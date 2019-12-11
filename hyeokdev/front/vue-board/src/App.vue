<template>
  <div id="app">
    <site-top></site-top>
    <boardHead></boardHead>
    <siteMenu></siteMenu>
    <boardBody></boardBody>
    <boardFooter></boardFooter>
  </div>
</template>

<script>
import boardHead from './components/board/BoardHead.vue'
import boardFooter from './components/board/BoardFooter.vue'
import boardBody from './components/board/BoardBody.vue'
import siteMenu from './components/site/SiteMenu.vue'
import siteTop from './components/site/SiteTop.vue'

export default {
  name: "app",
  components: {
    'boardHead': boardHead,
    'boardBody': boardBody,
    'boardFooter': boardFooter,
    'siteMenu': siteMenu,
    'siteTop': siteTop
  }
}
</script>

<style>
  body {
    text-align: center;
    background-color: white;
    height: 100%;
  }
  input {
    border-style: groove;
    width:200px;
  }
  button {
    border-style: groove;
  }
</style>
