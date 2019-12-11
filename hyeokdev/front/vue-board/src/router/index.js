import Vue from 'vue'
import Router from 'vue-router'
import boardBody from '@/components/board/BoardBody'
import boardWrite from '@/components/board/BoardWrite'
import boardDetail from '@/components/board/BoardDetail'
import boardList from '@/components/board/BoardList'
import boardUpdate from '@/components/board/BoardUpdate'
import homeBody from '@/components/site/HomeBody'
import loginBody from '@/components/site/LoginBody'
import joinBody from '@/components/site/JoinBody'

Vue.use(Router)

const router = new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      component: homeBody
    },
    {
      path: '/board',
      component: boardBody,
      children:[
        {
          path: '',
          component: boardList
        },
        {
          path: 'post',
          component: boardWrite
        },
        {
          path: ':boardId',
          name: 'boardDetail',
          component: boardDetail,
          props: true
        },
        {
          path: ':boardId/update',
          name: 'boardUpdate',
          component: boardUpdate,
          props: true
        }
      ]
    },
    {
      path: '/login',
      component: loginBody
    },
    {
      path: '/join',
      component: joinBody
    }
  ]
})

export default router
