<template>
  <div class="hello">
    <b-card
    header="오늘 해야 할 일"
    style="max-width: 40rem; margin: auto; margin-top: 10vh;"
    class="mb-2"
    border-variant="info"
    align="left">

      <b-form-group id="to-do-input">
        <b-container fluid>
          <b-row class="my-1">
            <b-col sm="10">
              <!-- v-model 에는 toDoItems.title로 명시 -->
              <b-form-input v-model="toDoItems.title" type="text" placeholder="새 할 일을 적으세요." />
            </b-col>
            <b-col sm="2">
              <b-button variant="outline-primary">추가</b-button>
            </b-col>
          </b-row>
        </b-container>
      </b-form-group>

      <b-list-group v-if="toDoItems && toDoItems.length">
        <b-list-group-item v-for="toDoItem of toDoItems" v-bind:data="toDoItem.title" v-bind:key="toDoItem.id">
          <b-form-checkbox v-model="toDoItem.done">
            {{ toDoItem.title }}
          </b-form-checkbox>
        </b-list-group-item>
      </b-list-group>
    </b-card>
  </div>
</template>

<!--
<template>
  <div class="hello">
    오늘 해야 할 일
    <ul v-if="toDoItems && toDoItems.length">
       <li v-for="toDoItem of toDoItems" v-bind:key="toDoItem.id" v-bind:data="toDoItem.title">
         {{toDoItem.title}}
       </li>
    </ul>
  </div>
</template>
-->

<script>
/*eslint-disable*/ 

import axios from 'axios' // 아까 받은 axios 패키지를 사용하기 위해 import한다

export default {
  name: 'hello',
  data: () => {
    return {
      toDoItems: []      // toDoItems를 빈 리스트로 초기화한다.
    }
  },
  created () {           // 초기화 함수를 정의 한다.
    axios.get('http://127.0.0.1:5000/todo/') // http://localhost:5000/todo/에 get call을 한다.
      .then(response => {
        console.log("axios 콜")
        this.toDoItems = response.data.map(r => r.data)     // 반환되는 값을 toDoItems에 저장한다.

      })
      .catch(e => {
        console.log('error : ', e)          // 에러가 나는 경우 콘솔에 에러를 출력한다
      })
  }
}
</script>


