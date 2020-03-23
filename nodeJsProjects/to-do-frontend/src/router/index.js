import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import ToDo from '@/ToDo.vue'

Vue.use(Router)

export default new Router({
/*eslint-disable*/ 
  mode: 'history',
  routes: [
    {
      path: '/',
      component: Hello
    },
    {
      path: '/todoList',
      component: ToDo
     }
  ]
})
