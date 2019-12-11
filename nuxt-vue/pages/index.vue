<template>
  <div id="wrap_content">
    <div class="wrap_head">
      <div class="header">
          <div class="logo">
            <img src="../static/images/skyscanner_logo.png" alt="logo" id="main_logo">
          </div>
          <nav class="header_navi">
            <ul class="navi_list">
              <li>
                <a href="#">도움말</a>
              </li>
              <li>
                <button class="header_btn">
                  <div class="button_inner_info">
                    한국어
                    <img src="../static/images/kr.png" alt="국기" id="country_flag">대한민국
                    <span class="money">₩ KRW</span>
                  </div>
                </button>
              </li>
              <li>
                <button v-on:click="goLogin" class="header_btn">
                  <span class="login_text" >
                    {{ btnMsg }}
                  </span>
                </button>
              </li>
            </ul>
          </nav>
      </div>
      <div class="header_tab">
        <nav id="howto_navi">
          <div class="header_tab_item">
            <a href="#" class="tab on">
              항공권
            </a>
          </div>
          <div class="header_tab">
            <a href="#" class="tab">
              호텔
            </a>
          </div>
          <div class="header_tab">
            <a href="#" class="tab">
              렌터카
            </a>
          </div>
        </nav>
      </div>
    </div>
    <div class="visual_zone">
      <div class="visual_content">
        <h1 class="visual_h1">어디로 떠나볼까요?</h1>
        <div class="visual_search">
          <div>
          <label class="search_radio">
            <input type="radio" name="howtogo" checked>
            <span class="radio_span">
              왕복
            </span>
          </label>
          <label class="search_radio">
            <input type="radio" name="howtogo">
            <span class="radio_span">
              편도
            </span>
          </label>
          <label class="search_radio">
            <input type="radio" name="howtogo">
            <span class="radio_span">
              다구간
            </span>
          </label>
          <a href="#" class="map_search">지도로 검색하기</a>
          </div>
          <div class="search_detail">
            <div class="search_detail_form">
              <label for="startSpot" class="search_inp_label">
                출발지
              </label>
              <input type="text" name="startSpot" class="first_inp spot_inp" value="부산 김해 (PUS)" placeholder="국가,도시 또는 공항">
              <button class="change_btn">
                <img src="../static/images/change_btn.png" alt="교환 버튼 이미지" id="change_img">
              </button>
            </div>
            <div class="search_detail_form">
            </div>
            <div class="search_detail_form">
              <label for="endSpot" class="search_inp_label">
                도착지
              </label>
              <input type="text" name="endSpot" class="spot_inp" value="제주공항 (CJU)" placeholder="국가,도시 또는 공항">
            </div>
            <div class="search_detail_form">
              <label for="startDate" class="search_inp_label">
                가는날
              </label>
              <input type="text" name="startDate" class="date_inp" value="2019. 11. 25." readonly>
            </div>
            <div class="search_detail_form">
              <label for="endDate" class="search_inp_label">
                오는날
              </label>
              <input type="text" name="endDate" class="date_inp" value="2019. 11. 25." readonly>
            </div>
            <div class="search_detail_form">
              <label for="optDetail" class="search_inp_label">
                좌석 등급 및 승객
              </label>
              <input type="text" name="optDetail" class="last_inp" value="1 성인, 일반석" readonly>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import cookie from 'js-cookie';

export default {
  data: function() {
    return {
      isLogin : false,
      btnMsg : '로그인'
    }
  },
  components: {
  },
  methods: {
    goLogin() {
      if(this.isLogin) {
        cookie.remove('access_token');
        this.isLogin = false;
        this.btnMsg = '로그인';
      } else {
        this.$router.push('/user/login');
      }
    },
  },
  created: function() {
    if(cookie.get('access_token')!=null) {
      this.isLogin = true;
      this.btnMsg = '로그아웃';
    }
  }
};
</script>

<style>
* {
    font: inherit;
}
a {
  text-decoration: none;
}
 #wrap_content {
   height: auto;
 }
 .wrap_head {
   display: flex;
   margin: 0 auto;
   flex-direction: column;
   width:1048px;
}
 #main_logo {
   width: 175.825px;
   height: 30px;
 }
 .logo {
   float: left;
   padding: 1.5rem 0;
   width: 10.98958rem;
 }
 .header_navi {
   float: right;
   padding: 1.5rem 0 0;
   text-align: center;
 }
 .navi_list {
   list-style: none;
 }
 .navi_list li {
   display: inline-block;
   align-self: center;
   margin-left: 12px;
 }
 .navi_list li a {
   line-height: 1.125rem;
   font-size: .75rem;
   color: #0770e3;
 }
 .button_inner_info {
   display: flex;
   align-items: center;
   font-size: .75rem;
   font-weight: 400;
 }
 #country_flag {
   margin-left: 0.5em;
   margin-right: 0.25em;
   height: 0.75rem;
   border: 1px solid #dddde5;
   box-sizing: content-box;
 }
 .money {
   color: #b2b2bf;
   margin-left: .5em;
 }
 .header_btn {
   background: white;
   box-shadow: inset 0 0 0 2px #dddde5;
   border: 0;
   border-radius: .25rem;
   cursor: pointer;
   padding: .375rem 1.125rem;
   font-weight: 700;
   line-height: 1.5rem;
   text-align: center;
   vertical-align: middle;
 }
 .header_btn:hover {
   box-shadow: inset 0 0 0 2px #0770e3;
 }
 .login_text {
   color: #0770e3;
   font-weight: 700;
   font-size: 1.1875rem;
   display: flex;
 }

 /* how to navi */
 #howto_navi {
   display: flex;
 }
 .tab {
   display: flex;
   height: 2.25rem;
   padding: .3125rem .75rem;
   border-top-left-radius: .25em;
   border-top-right-radius: .25em;
   background: #0770e3;
   color: white;
   font-weight: 700;
   margin-right: .3em;
 }
 .on {
   background: #042759;
 }

 /* visual zone */
  .visual_zone {
    background: url('../static/images/skyscanner_visual.webp');
    background-size: cover;
    background-position: 50% 50%;
    min-height: 550px;
  }
  .visual_content {
    width: 100%;
    margin: 0 auto;
    padding: 1.5rem 0;
    max-width: 1096px;
  }
  .visual_h1 {
    color: #fff;
    font-weight: 700;
    font-size: 3.75rem;
    padding: 0 0 1.875rem 1.5rem;
    margin-top: 1em;
  }
  .visual_search {
    background-color: #02122c;
    padding: 1.5rem;
    border-radius: .25rem;
    margin: 0 auto;
    min-height: 173px;
  }
  .radio_span {
    color: #fff;
  }
  .map_search {
    color: #fff;
    float: right;
  }
  .map_search:hover {
    text-decoration: underline;
  }
  .search_inp_label {
    color: #fff;
    font-size: .75em;
    font-weight: 600;
    display: block;
  }
  .search_detail_form {
    float: left;
    margin-top: 1em;
  }
  .change_btn {
    background: white;
    border: 0.0625rem solid #b2b2bf;
    border-left: none;
    height: 3rem;
    cursor: pointer;
    float: right;
  }
  .search_detail_form input {
    height: 3rem;
    border: 0.0625rem solid #b2b2bf;
    border-right: none;
    padding: .375rem .75rem;
  }
  .first_inp {
    border-top-left-radius: 4px;
    border-bottom-left-radius: 4px;
  }
  .last_inp {
    width: 270px;
    border-top-right-radius: 4px;
    border-bottom-right-radius: 4px;
    background: white url('../static/images/triangle_down.png') no-repeat 15rem 1.1rem;
    background-size: 0.75rem 0.75rem;
  }
  .date_inp {
    width: 130px;
  }
  .spot_inp {
    width: 240px;
  }
</style>
