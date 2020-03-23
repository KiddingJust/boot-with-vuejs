<template>
  <div class="hello">
    오늘 해야 할 일
    <ul v-if="toDoItems && toDoItems.length">
       <li v-for="toDoItem of toDoItems" v-bind:key="toDoItem">
         {{toDoItem.title}}
       </li>
    </ul>
  </div>
</template>

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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #35495E;
}
</style>
