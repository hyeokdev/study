<template>
    <div id="login_wrap">
        <div class="login_box">
            <h1 class="login_h1">Skyscanner</h1>
            <div class="login_inp">
                <label for="userid" class="login_label">아이디</label>
                <input type="text" v-model="userid" class="login_input" placeholder=" ID">
            </div>
            <div class="login_inp">
                <label for="password" class="login_label">비밀번호</label>
                <input type="password" v-model="password" class="login_input" placeholder=" PASSWORD">
            </div>
            <button class="login_btn" @click="login()">
                로그인
            </button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import cookie from 'js-cookie';

export default {
    data: function() {
        return {
            userid: '',
            password: ''
        }
    },
    methods: {
        login() {
            axios.get('/api/user/login', {
                params: {
                'userName': this.userid,
                'userPassword': this.password,
                }
            }).then((response) => {
                cookie.set('access_token', response.data.access_token);
                this.$router.push('/');
            }).catch((exception) => {
                console.log('실패');
            })
        }
    }
};
</script>

<style>
    #login_wrap {
        margin: 10% 40%;
    }
    .login_h1 {
        font-size: 2em;
        color: #0770e3;
        text-align: center;
        margin-bottom: 1.25em;
    }
    .login_box {
        border: 2px solid #dddde5;
        padding: 10% 10%;
        border-radius: 10px;
        width: 100%;
    }
    .login_label {
        display: none;
    }
    .login_input {
        border-radius: 3px;
        border: 1.5px solid #dddde5;
        width: 100%;
        height: 2.5em;
        margin-bottom: .5em;
    }
    .login_btn {
        width: 100%;
        height: 2.5em;
        border-radius: 3px;
        border: 0;
        background: #0770e3;
        color: #fff;
        cursor: pointer;
    }
    .login_btn:hover {
        background: #075fe3;
    }
</style>
