<template>
  <div id="app">
    <TodoHeader></TodoHeader>
    <!-- 하위 컴포넌트에서 발생시킨 이벤트 이름="현재 컴포넌트의 메서드" -->
    <TodoInput v-on:addTodoItem="addOneItem"></TodoInput>
    <!-- 내려보낼 프롭스 속성 이름 = "현재 컴포넌트 데이터 속성" -->
    <TodoList v-bind:propsdata="todoItems" v-on:removeItem="removeOneItem" v-on:toggleItem="toggleOneItem"></TodoList>
    <TodoFooter v-on:clearAll="clearAllItems"></TodoFooter>
    <Calendar>Calendar</Calendar>
  </div>
</template>

<script>
/*eslint-disable*/ 
import TodoHeader from './components/TodoHeader.vue'
import TodoInput from './components/TodoInput.vue'
import TodoList from './components/TodoList.vue'
import TodoFooter from './components/TodoFooter.vue'
import {Datepicker, Timepicker, DatetimePicker} from '@livelybone/vue-datepicker'


//로컬 컴포넌트를 설정하는 경우에는
//new Vue에서 components 속성에서 설정해주었음. 

export default {
  data: function(){
    return {
      todoItems: []
    }
  },
  methods: {
    addOneItem: function(todoItem){
        //체크 되었는지 아닌지는 completed로 판단. 
        var obj = {completed: false, item: todoItem};
        // 저장하는 로직. value 부분을 String으로 변환
        localStorage.setItem(todoItem, JSON.stringify(obj));
        //화면에 있는 할일 목록까지 동기화. 배열에서 맨 끝에 하나의 값 넣는 것. 
        this.todoItems.push(obj);
    },
    removeOneItem: function(todoItem, index){
      //예제에서는 key와 value가 동일. key를 넣어줌. 
      localStorage.removeItem(todoItem.item);
      //localStorage 삭제 후 스크립트 화면에도 반영해주어야 함
      //splice는 자바스크립트 배열 API. 특정 인덱스에서 1개를 지우겠다는 것. 
      //변경 후 새로운 배열을 반환하는 api
      this.todoItems.splice(index, 1);
    },
    toggleOneItem: function(todoItem, index){
      // todoItem.completed = !todoItem.completed;
      this.todoItems[index].completed = !this.todoItems[index].completed

      //체크 내역을 localStorage에 저장.
      //그런데 localStorage에 update 치는 API는 없음.
      //그래서 삭제 후 다시 저장. 
      localStorage.removeItem(todoItem.item);
      localStorage.setItem(todoItem.item, JSON.stringify(todoItem));
    },
    clearAllItems: function(){
      localStorage.clear();
      this.todoItems = [];
    }
  },
  created: function() {
    if (localStorage.length > 0){
      for (var i=0; i<localStorage.length; i++){
        if(localStorage.key(i) !== 'loglevel:webpack-dev-server'){
          //다시 JSON으로 변환.
          //로컬 스토리지의 특성상 어쩔 수 없이 String전환 후 다시 Object로
          //localStorage.key(i) 에서 item을 가져오고 이걸 다시 parsing 하겠다는 것. 
          console.log(JSON.parse(localStorage.getItem(localStorage.key(i))));
          this.todoItems.push(JSON.parse(localStorage.getItem(localStorage.key(i))));
          // this.todoItems.push(localStorage.key(i))
        }
      }
    }
  },
  components: {
    'TodoHeader' : TodoHeader,
    'TodoInput' : TodoInput,
    'TodoList' : TodoList,
    'TodoFooter' : TodoFooter,
    'Calendar' : Datepicker
  }
}

</script>

<style>
body{
  text-align: center;
  background-color: #F6F6F6;
}

input {
  border-style: groove;
  width: 200px;
}

button {
  border-style: groove;
}

.shadow {
  box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.03);
}
</style>
