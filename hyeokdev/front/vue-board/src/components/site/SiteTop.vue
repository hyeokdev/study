<template>
  <div class="container">
      <div class="pull-right">
          <router-link to="/"><span class="glyphicon glyphicon-home"></span>HOME</router-link>
          <router-link to="/login" @click.native="account">
            <span class="glyphicon glyphicon-user"/>
            {{ loginMsg }}
          </router-link>
      </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      loginMsg : 'LOGIN',
      isLogined: false
    }
  },
  methods: {
    account: function() {
      if(this.isLogined) {
        this.$cookie.delete('access_token')
        this.isLogined = false;
        this.loginMsg = 'LOGIN'
      }
    }
  },
  created: function() {
    this.EventBus.$on("login-eventbus", login => {
      this.isLogined = login
      this.loginMsg ='LOGOUT'
    })
    if(this.$cookie.get('access_token')!=null) {
      this.isLogined = true;
      this.loginMsg = 'LOGOUT'
    }
  }
}
</script>

<style>

</style>