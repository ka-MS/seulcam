<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<meta http-equiv="X-UA-Compatible" content="IE=edge">
				<meta name="viewport" content="width=device-width, initial-scale=1.0 user-scalable=no">
				<title>캠핑장 리스트</title>
				<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script> -->
				<link rel="stylesheet" href="/resources/css/camp/switch.css">
				<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
				<link rel="stylesheet" href="/resources/css/fonts.css">
				<link rel="stylesheet" href="/resources/css/camp/campList.css">
				<link rel="shortcut icon" href="/resources/images/faviconlogo.ico" type="image/x-icon">
				<link rel="icon" href="/resources/images/faviconlogo.ico" type="image/x-icon">
				<link rel="stylesheet"
					href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
				<!-- jQuery -->
				<script src="../../../resources/js/jquery-3.6.1.min.js"></script>
				<!-- slick -->
				<link rel="stylesheet" type="text/css"
					href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
				<script type="text/javascript"
					src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
				<script src="https://kit.fontawesome.com/cb40f8d009.js" crossorigin="anonymous"></script>
			</head>

			<body>
				<!-- 헤더&메뉴바 -->
				<header>
					<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
				</header>
				<!-- 검색 -->


				<!-- 컨텐츠 -->

				<div class="body-wrapper">
					<div class="campListMain">
						<div class="search_area">
							<h2><a href="/camp/campList.kh">최근 인기 캠핑장 추천, 슬캠 ${msg }</a></h2>
							<div class="searchCity">
								<label for="city_select">
									<p>지역 </p>
								</label>
								<select class="selectList form-select" name="city_select" id="city_select">
									<option value="" selected>지역 시/도 전체</option>
									<option value="강원도">강원도</option>
									<option value="경기도">경기도</option>
									<option value="경상남도">경상남도</option>
									<option value="경상북도">경상북도</option>
									<option value="광주시">광주시</option>
									<option value="대구시">대구시</option>
									<option value="대전시">대전시</option>
									<option value="부산시">부산시</option>
									<option value="서울시">서울시</option>
									<option value="세종시">세종시</option>
									<option value="울산시">울산시</option>
									<option value="인천시">인천시</option>
									<option value="전라남도">전라남도</option>
									<option value="전라북도">전라북도</option>
									<option value="제주도">제주도</option>
									<option value="충청남도">충청남도</option>
									<option value="충청북도">충청북도</option>
								</select>
							</div>
							<div class="searchCity">
								<label for="category_select">
									<p>카테고리 </p>
								</label>
								<select class="selectList form-select" name="category_select" id="category_select">
									<option value="" selected>카테고리 전체</option>
									<option value="글램핑">글램핑</option>
									<option value="일반야영장">일반야영장</option>
									<option value="자동차야영장">자동차야영장</option>
									<option value="카라반">카라반</option>
								</select>
							</div>
							<div class="switch_area">

								<span id="switchName">
									예약가능 캠핑장만 보기
								</span>
								<span id="switch">
									<label class="switch-button">
										<input type="checkbox" name="registCheck" />
										<span class="onoff-switch"></span>
									</label>
								</span>
							</div>

							<div class="input-group mb-3">
								<input type="text" class="searchForm form-control" placeholder="검색어를 입력해주세요">
								<button class="btn btn-outline-secondary" type="button" id="button-addon2"
									onclick="searchForm()">Search</button>
							</div>

						</div>


						<div class="list_area">
							<div class="word_area">
								<h5>검색결과</h5>
							</div>
							<div id="list_area">


							</div>
						</div>

						<!-- 푸터 -->


						<script>
							var page = 0;
							var city = "";
							var category = "";
							var search = "";
							var regist = "N";
							var listUrl = "/camp/campListShow.kh";
							var scrollCheck = "list";

							// 좋아요 버튼 클릭시 동작
							function likeButton(AcontentId, AmapX, AmapY) {
								const data = {
									"memberId": "${sessionScope.loginUser.memberId}",
									"contentId": AcontentId,
									"campId": AcontentId,
									"mapX": AmapX,
									"mapY": AmapY
								}
								// var memberId = "${sessionScope.loginUser.memberId}";
								// var contentId = AcontentId;
								// var mapX = AmapX;
								// var mapY = AmapY;
								console.log(data);
								if (data.memberId == "") {
									alert("로그인이 필요한 서비스 입니다.")
								} else {
									$.ajax({
										url: "/camp/campLike.kh",
										type: "get",
										data: data,
										async: false,
										success: function (result) {
											if (result == "none") {
												alert("로그인이 필요한 서비스 입니다.");
												location.href = "/camp/campList.kh";
											} else if (result == "abnormal") {
												alert("비정상적인 접근입니다.");
												location.href = "/camp/campList.kh";
											} else {
												likeCount(data);
												likeCheck(data);
											}
										},
										error: function (request, status, error) {
											console.log("code: " + request.status)
											console.log("message: " + request.responseText)
											console.log("error: " + error);
										}

									})
								}
							}

							// 좋아요 갯수 카운트
							function likeCount(data) {
								$.ajax({
									url: "/camp/campLikeCount.kh",
									type: "get",
									data: {
										"campId": data.contentId
									},
									success: function (result) {
										$("#likeCount-" + data.contentId + "").html(result)
									},
									error: function (request, status, error) {
										console.log("code: " + request.status)
										console.log("message: " + request.responseText)
										console.log("error: " + error);
									}

								})
							}

							//로그인 한 아이디가 좋아요를 이미 눌렀을경우 표시
							function likeCheck(data) {
								if (data.memberId != "") {
									$.ajax({
										url: "/camp/campLikeCheck.kh",
										type: "get",
										data: data,
										success: function (result) {
											if (result > 0) {
												$("#like-" + data.contentId + "").addClass("alreadyLike");
											} else {
												$("#like-" + data.contentId + "").removeClass("alreadyLike");
											}
										},
										error: function (request, status, error) {
											console.log("code: " + request.status)
											console.log("message: " + request.responseText)
											console.log("error: " + error);
										}

									})

								}

							}

							// 리스트 생성 function
							function urlLoad() {
								console.log(search)
								if (scrollCheck != "scroll") {
									$(".word_area").html("<h5 id='searchResult'>" + city + " " + category + " " + search + " 검색결과</h5> ")
								}
								$.ajax({
									url: listUrl,
									type: "GET",
									data: {
										"page": page,
										"city": city,
										"category": category,
										"search": search,
										"regist": regist
									},
									dataType: "json",
									async: false,
									success: function (data) {

										var str = "";
										for (var i = 0; i < data.camp.length; i++) {
											
											var avg = parseFloat(data.camp[i].starAvg).toFixed(2);
											if(avg == 'NaN'){
												avg = '0.00'
											}
											str += "<div class='camp_List' id='campsite-" + data.camp[i].contentId + "'>"
											str += "<a href='/camp/campDetail.kh?contentId=" + data.camp[i].contentId + "' data-id='" + data.camp[i].contentId + "'>"
											if (data.camp[i].firstImageUrl == null) {
												str += "<div style='height: 225px; background: url(https://3.bp.blogspot.com/-ZKBbW7TmQD4/U6P_DTbE2MI/AAAAAAAADjg/wdhBRyLv5e8/s1600/noimg.gif) no-repeat center center #343a40; background-size: 100%;'></div></a>"
											} else {
												str += "<div style='height: 225px; background: url(" + data.camp[i].firstImageUrl + ") no-repeat center center #343a40; background-size: 100%;'></div></a>"
											}
											str += "<div class='card-body 'style='padding-top: 8px;'><div class='row'><div class='col-8'><div class='text-right tt' stlyle='padding: 0px 12px;'><small class='text-muted'>" + data.camp[i].induty + "</small></div>"
											str += "<a href='/camp/campDetail.kh?contentId=" + data.camp[i].contentId + "' data-id='" + data.camp[i].contentId + "'><h5 class='card-title tt'>" + data.camp[i].facltNm + "</h5><p class='card-text tt'>" + data.camp[i].addr1 + "</p></a><div style='display:flex;'>"
											if (data.camp[i].likeCheck > 0) {
												str += "<div class='likeBtn alreadyLike' id='like-" + data.camp[i].contentId + "' onclick='likeButton(" + data.camp[i].contentId + "," + data.camp[i].mapX + "," + data.camp[i].mapY + ")' ><svg class='heart' xmlns='http://www.w3.org/2000/svg' width='15' height='15' fill='currentColor' class='bi bi-heart-fill' viewBox='0 0 16 16'>"
											} else {
												str += "<div class='likeBtn' id='like-" + data.camp[i].contentId + "' onclick='likeButton(" + data.camp[i].contentId + "," + data.camp[i].mapX + "," + data.camp[i].mapY + ")' ><svg class='heart' xmlns='http://www.w3.org/2000/svg' width='15' height='15' fill='currentColor' class='bi bi-heart-fill' viewBox='0 0 16 16'>"
											}
											str += "<path fill-rule='evenodd' d='M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z'></path></svg><span class='likeCount' id='likeCount-" + data.camp[i].contentId + "'>" + data.camp[i].likeCount + "</span></div>"
											str += "<div  class='starCzone' > <svg class='starC' xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-star-fill' viewBox='0 0 16 16'><path d='M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z'/></svg></svg><span class='starCount'>" + avg + "</span></div>"
											if (data.camp[i].walking > 0) {
												str += "<div class='walking done'><i class='fas fa-walking'></i></div>"
											} else {
												str += "<div class='walking'><i class='fas fa-walking'></i></div>"
											}
											
											str += "</div></div><div class='col-4' style='padding-left:0;'>"
											
											if (data.camp[i].registAvi == 'Y') {
												str += "<div class='booking'><div style='display:flex; flex-direction: column;'><p style='font-size : 10pt; color : #dc3545'><b>예약가능</b></p><p style='font-size : 16pt;'><b>" + data.camp[i].minPrice.toLocaleString('ko-KR') + "원~</b></p></div></div>"
											}
											str += "</div></div></div></div><hr>"
										}
										if (scrollCheck == "scroll") {
											$("#list_area").append(str);
										} else {
											$("#list_area").html(str);
											if (data.camp.length == 0) {
												var sr = "<b> [0개]</b>";
											} else {
												var sr = "<b> [" + data.campListCount + "개]</b>";
											}
											$("#searchResult").append(sr);
										}
										page++
									},
									error: function (request, status, error) {
										console.log("code: " + request.status)
										console.log("message: " + request.responseText)
										console.log("error: " + error);
									}
								})
							}

							//리스트 진입시 목록 출력
							$(document).ready(function () {
								urlLoad();
							})
							//자동 스크롤시 페이지 갱신 ajax
							function scrollload() {
								scrollCheck = "scroll"
								// page++;
								urlLoad();
							}
							//자동 스크롤시 페이지 갱신 코드
							$(window).scroll(function () {
								// setTimeout(function(){
								var scrollHeight = $(window).scrollTop() + $(window).height();
								var documentHeight = $(document).height();
								//현재 높이 측정
								// console.log(scrollHeight)
								// console.log(documentHeight)
								if (scrollHeight + page >= documentHeight - 1) {
									console.log()

									scrollload()
								}
								// },500)
							});

							// 예약가능 스위치 활성화
							$("input[name='registCheck']").change(function () {
								scrollCheck != "list"
								if (this.checked == true) {
									regist = "Y"
									if (page != 0) {
										page = 0;
									}
									urlLoad()
								} else {
									regist = "N"
									if (page != 0) {
										page = 0;
									}
									urlLoad()
								}
							})

							// select값 으로 검색결과 출력
							$("select").change(function () {
								if (this.name == "city_select") {
									city = $(this).val();
									var city1 = "\'" + $(this).val() + "\'";
								} else if (this.name == "category_select") {
									category = $(this).val();
									var category1 = "\'" + $(this).val() + "\'";
								}
								scrollCheck != "list"
								if (page != 0) {
									page = 0;
								}
								urlLoad();
							});

							//검색어 입력시 리스트 출력
							function searchForm() {
								if ($(".searchForm").val().trim() != "") {
									search = $(".searchForm").val();
									scrollCheck != "list"
									if (page != 0) {
										page = 0;
									}
									urlLoad();
								} else {
									search = "";
									if (page != 0) {
										page = 0;
									}
									urlLoad();
								}
							}
						</script>
			</body>

			</html>