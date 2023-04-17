package com.kh.seulcam.campBooking.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.seulcam.campBooking.domain.BookingStatus;
import com.kh.seulcam.campBooking.domain.CampBooking;
import com.kh.seulcam.campBooking.domain.bookingStatusSearch;
import com.kh.seulcam.campBooking.service.CampBookingService;
import com.kh.seulcam.campBooking.store.CampBookingStore;
import com.kh.seulcam.order.domain.OrderCancle;
import com.kh.seulcam.order.domain.OrderPay;
import com.kh.seulcam.order.service.OrderService;
import com.kh.seulcam.point.domain.Point;

@Service
public class CampBookingServiceImpl implements CampBookingService{
    @Autowired
    private SqlSessionTemplate session;
    
    @Autowired
    private CampBookingStore bStore;
    
    @Autowired
	private OrderService oService;
    
    @Override
    public int campBookingRegist(CampBooking cBooking) {
        int result = bStore.bookingInsert(session,cBooking);
        return result;
    }
    
    @Override
    public int bookingStatus(CampBooking cBooking) {
    	BookingStatus bs = new BookingStatus(
				cBooking.getSiteNo(),
				cBooking.getMemberId(),
				cBooking.getFirstDay(),
				cBooking.getTotalDay() - 1);
        int bsInsert = bStore.bStatusInsert(session,bs);
        return bsInsert;
    }
    
    @Override
    public String bookingCount(CampBooking cBooking) {
    	bookingStatusSearch bss = new bookingStatusSearch(
				cBooking.getSiteNo(),
				cBooking.getFirstDay(),
				cBooking.getLastDay());
        String result = bStore.selectBookCount(session,bss);
        return result;
    }
    
    @Override
	public String bookingCount(bookingStatusSearch bss) {
		String result = bStore.selectBookCount(session,bss);
        return result;
	}

    

    @Override
    public CampBooking printBookingInfo(String bookingNo) {
        CampBooking campBooking = bStore.selectBookinginfo(session,bookingNo);
        return campBooking;
    }

    @Override
    public List<CampBooking> BooingListView(String memberId) {
        System.out.println("서비스");
        List<CampBooking> cbList = bStore.selectBookList(session,memberId);
        return cbList;
    }

    @Override
    public int changeBookingStatus(OrderCancle orderCancle) {
        int result = bStore.updateBookingStatus(session, orderCancle);
        return result;
    }

    @Override
    public int deleteBookStatus(int orderNo) {
        int result = bStore.deleteBookStatus(session,orderNo);
        return result;
    }
    
    //트랜잭션 처리
    @Transactional
	@Override
	public String createReservation(CampBooking cBooking, OrderPay orderPay) {
    	// 잔여좌석 없으면 예약안되도록 체크
		String resultbss = bookingCount(cBooking);
		if (resultbss != null) {
			if (Integer.parseInt(resultbss) < 1) {
				return "fail";
			}
		}
		
		// 예약저장
		campBookingRegist(cBooking);
		
		// 결제테이블에 정보 넣기
		orderPay.setOrderNo(cBooking.getBookingNo());
		oService.registOrderPrice(orderPay);
		
		// 포인트 차감
		Point pointUse = new Point();
		pointUse.setMemberId(cBooking.getMemberId());
		pointUse.setPoint(cBooking.getBookUsePoint() + "");
		oService.registUsePoint(pointUse);
		
		// 포인트 적립
		oService.registGetPoint(String.valueOf(cBooking.getBookGetPoint()),cBooking.getMemberId());
		
		// 예약 현황 저장
		bookingStatus(cBooking);
		
		String bookingNo = String.valueOf(cBooking.getBookingNo());
		
		return bookingNo;
	}

}
