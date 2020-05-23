<template>
  <div class="hello">
    <b-card
    header="ToDoList"
    style="max-width: 40rem; margin: auto; margin-top: 10vh;"
    class="mb-2"
    border-variant="info"
    align="left">

      <b-form-group id="to-do-input">
        <b-container fluid>
          <VueDatepicker v-on:selected="selectDate" v-model="myDate" > </VueDatepicker>
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
          <b-form-checkbox v-model="toDoItem.selected" v-on:change="addNextToDo(toDoItem)">
          </b-form-checkbox>
          <span v-if="toDoItem.done" style="text-decoration: line-through; color:#D3D3D3;">
            {{ toDoItem.title }}</span>
          <span v-else>{{ toDoItem.title }}</span>
          <span class = "btn">
          <i class="completeBtn fas fa-check"
             v-model="toDoItem.done"
             v-on:click="markDone(toDoItem)"></i>
          <i class="removeBtn fas fa-trash-alt" v-on:click="removeTodo(toDoItem)"></i>
          </span>
        </b-list-group-item>
      </b-list-group>
      <b-card-footer> 
        <b-button-group class="Footer">
          <b-button class="clearBtn" variant="danger" v-on:click="clearAll">Clear All</b-button>
        </b-button-group>
        <span class = "footerBtn">
          <i class="nextBtn fas fa-angle-double-right"
             v-on:click="toNextDay()">  </i>
        </span>
      </b-card-footer>
    </b-card>
  </div>
</template>

<script>

/*eslint-disable*/ 

import axios from 'axios' // 아까 받은 axios 패키지를 사용하기 위해 import한다
import {Datepicker, Timepicker, DatetimePicker} from '@livelybone/vue-datepicker'
import VueDatepicker from 'vuejs-datepicker';

let baseUrl = 'http://35.196.12.111:80/todo/'

export default {
  name: 'hello',
  data: () => {
    return {
      toDoItems: [],      // toDoItems를 빈 리스트로 초기화한다.
      newToDoItemRequest: {}, // 새 To Do Item 을 저장할 Object
      nextToDoItems: [],
      myDate: new Date().toISOString().split('T')[0]
    }
  },
  components: {
    VueDatepicker,
    Datepicker,
    Timepicker,
    DatetimePicker
  },
  //메서드 설정
  methods: {
    initToDoList: function(myDate) {
      let vm = this
      var dateTime = vm.myDate
      console.log("왜 바뀐 거 적용 안되니")
      console.log("dateTime: " + dateTime)
      console.log("typeofconverted dateTime: " + typeof(dateTime))
      if(typeof(dateTime) == 'object'){
        console.log("여기 들어오나")
        dateTime = dateTime.toISOString().split('T')[0]
      }
      axios.get(baseUrl + dateTime)        // http://localhost:5000/todo/에 get call을 한다.
        .then(response => {     // 반환되는 값을 toDoItems에 저장한다.
          vm.toDoItems = response.data.map(r => r.data)
          vm.nextToDoItems = []
          console.log("한번 더 체크 dateTime: " + dateTime)
          // if(dateTime == new Date().toISOString().split('T')[0]){
          //   vm.myDate = dateTime.toISOString().split('T')[0]
          // }
        })
        .catch(e => {
          console.log('error : ', e)
        })
    },
    createToDo: function(){
      event.preventDefault()
      let vm = this
      if(!vm.newToDoItemRequest.title) return // To Do Item의 제목이 없으면 아무것도 안하고 리턴
      console.log("왜 수정이 안되니")
      axios.post(baseUrl + "create", vm.newToDoItemRequest) //스프링부트 Restful API에 post call
        .then(response => {
          console.log(response)
          vm.newToDoItemRequest = {}
          vm.nextToDoItems = []
          vm.initToDoList(vm.myDate)
        })
        .catch(error => {
          console.log(error)
        })
    },
    markDone: function (toDoItem){
      if (!toDoItem) return     //null이면  return
      let vm = this
      toDoItem.done = !toDoItem.done    //true <-> false  
                                        //여기까지만 해도 체크 저장. 하지만 새로고침하면 원상복귀
      axios.put(baseUrl, toDoItem)
        .then(response => {
          vm.initToDoList(vm.myDate)           //리스트 갱신
        }).catch(error => {
          console.log(error)
        })
    },
    removeTodo: function (toDoItem){
      if (!toDoItem) return
      let vm = this
      axios.post(baseUrl + "remove", toDoItem)
        .then(response => {
          vm.initToDoList(vm.myDate)
        }).catch(error => {
          console.log(error)
        })
    },
    clearAll: function (){
      let vm = this
      axios.post(baseUrl + "clearAll", vm.myDate)
        .then(response => {
          //vm.initToDoList()
          vm.newToDoItemRequest = {}
          vm.initToDoList(vm.myDate)
        }).catch(error => {
          console.log(error)
        })
    },
    addNextToDo: function (toDoItem){
      if(typeof(toDoItem.selected) == 'undefined') toDoItem.selected = false
      //console.log(toDoItem.selected)
      let vm = this

      var items = vm.nextToDoItems;
    
      //nextToDoItems에 포함되어 있는지 체크. 
      var inCheck = items.find(function(item){
        return item.id === toDoItem.id
      })

      //체크 되었는데, 이전에 포함 안 되어있으면 push
      //체크 해제었는데 이전에 포함 되어있으면 제거
      //이전 포함 여부를 따지는 이유: change로만 나누기 때문. 
      if(!toDoItem.selected){
        //if(!inCheck || inCheck == 'undefined'){
          items.push(toDoItem)
        //} 
      } else {
          var index = items.findIndex(function(item){
            return item.id === toDoItem.id
          })
          items.splice(index, 1) 
      }
    },
    toNextDay: function (){
      let vm = this
      axios.post(baseUrl + "toNextDay", vm.nextToDoItems)
        .then(response => {
          vm.nextToDoItems = []
          vm.initToDoList(vm.myDate)
        }).catch(error => {
          vm.nextToDoItems = []
          console.log(error)
        })
    },
    selectDate(date) {
      let vm = this
      vm.myDate = date.toISOString().split('T')[0]
      vm.initToDoList(vm.myDate)
    }
  },  
  created () {           // 초기화 함수를 정의 한다.
    this.initToDoList()
  }
}
</script>

<style>
.btn {
  margin-left: auto;
}
.completeBtn {
  color: #483D8B;
  margin-left: auto;
}
.removeBtn {
  margin-left: auto;
  color: #de4343;
}
.clearBtn {
  align: center;
  margin: auto;
}
.footerBtn {
  align: right;
  margin-left: auto;
}
</style>
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


        
    },

