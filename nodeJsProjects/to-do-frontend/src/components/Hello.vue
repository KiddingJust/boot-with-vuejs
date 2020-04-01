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
            <!--  <b-form-input v-model="toDoItems.title" type="text" placeholder="새 할 일을 적으세요." /> -->
              <b-form-input v-model="newToDoItemRequest.title" type="text"
                            placeholder="새 할 일을 적으세요." v-on:keyup.enter="createToDo" />
            </b-col>
            <b-col sm="2">
              <b-button variant="outline-primary" v-on:click="createToDo">추가</b-button>
            </b-col>
          </b-row>
        </b-container>
      </b-form-group>

      <b-list-group v-if="toDoItems && toDoItems.length">
        <b-list-group-item
          v-for="toDoItem of toDoItems"
          v-bind:data="toDoItem.id"
          v-bind:key="toDoItem.id" style="display: flex;">
          <b-form-checkbox
            v-model="toDoItem.done"
            v-on:change="markDone(toDoItem)">
          </b-form-checkbox>
          <span v-if="toDoItem.done" style="text-decoration: line-through; color:#D3D3D3;">
            {{ toDoItem.title }}</span>
          <span v-else>{{ toDoItem.title }}</span>
        </b-list-group-item>
      </b-list-group>
    </b-card>
  </div>
</template>

<script>

/*eslint-disable*/ 

import axios from 'axios' // 아까 받은 axios 패키지를 사용하기 위해 import한다


let baseUrl = 'http://127.0.0.1:5000/todo/'

export default {
  name: 'hello',
  data: () => {
    return {
      toDoItems: [],      // toDoItems를 빈 리스트로 초기화한다.
      newToDoItemRequest: {}  // 새 To Do Item 을 저장할 Object
    }
  },
  //메서드 설정
  methods: {
    initToDoList: function() {
      let vm = this
      axios.get(baseUrl)        // http://localhost:5000/todo/에 get call을 한다.
        .then(response => {     // 반환되는 값을 toDoItems에 저장한다.
          vm.toDoItems = response.data.map(r => r.data)
        })
        .catch(e => {
          console.log('error : ', e)
        })
    },
    createToDo: function(){
      event.preventDefault()
      let vm = this
      if(!vm.newToDoItemRequest.title) return // To Do Item의 제목이 없으면 아무것도 안하고 리턴
      axios.post(baseUrl, vm.newToDoItemRequest) //스프링부트 Restful API에 post call
        .then(response => {
          console.log(response)
          vm.initToDoList()
          vm.newToDoItemRequest = {}
        })
        .ceatch(error => {
          console.log(error)
        })
    },
    markDone: function (toDoItem){
      if (!toDoItem) return     //nullㅇㅣ면  return
      let vm = this
      toDoItem.done = !toDoItem.done    //true <-> false  
                                        //여기까지만 해도 체크 저장. 하지만 새로고침하면 원상복귀

    
      axios.put(baseUrl, toDoItem)
        .then(response => {
          vm.initToDoList()           //리스트 갱신
        }).catch(error => {
          console.log(error)
        })
    }
  },  
  created () {           // 초기화 함수를 정의 한다.
    this.initToDoList()
  }
}
</script>

<!--
    createToDo: function (event) {
      event.preventDefault()
      let vm = this
      if (!vm.newToDoItemRequest.title) return // To Do Item의 제목이 없으면 아무것도 안하고 리턴
      axios.post(baseUrl, vm.newToDoItemRequest  //스프링부트 Restful API에 post call
        .then(response => {
          console.log(response)
        })
        .catch(error => {
          console.log(error)
        }) 
    }

