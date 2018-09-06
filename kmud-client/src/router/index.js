import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Status from '@/components/Status'
import Artifact from '@/components/Artifact'
import Bag from '@/components/Bag'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/status',
      name: 'Status',
      component: Status
    },
    {
      path: '/artifact',
      name: 'Artifact',
      component: Artifact
    },
    {
      path: '/bat',
      name: 'Bag',
      component: Bag
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
