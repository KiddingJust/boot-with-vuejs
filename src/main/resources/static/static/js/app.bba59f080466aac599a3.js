webpackJsonp([1],{137:function(t,e,o){"use strict";e.a={name:"app"}},138:function(t,e,o){"use strict";var n=o(314),a=o.n(n),s=o(344),i=o.n(s),c=o(158),r=o(369),l="http://35.196.12.111:80/todo/";e.a={name:"hello",data:function(){return{toDoItems:[],newToDoItemRequest:{},nextToDoItems:[],myDate:(new Date).toISOString().split("T")[0]}},components:{VueDatepicker:r.a,Datepicker:c.a,Timepicker:c.c,DatetimePicker:c.b},methods:{initToDoList:function(t){var e=this,o=e.myDate;console.log("왜 바뀐 거 적용 안되니"),console.log("dateTime: "+o),console.log("typeofconverted dateTime: "+(void 0===o?"undefined":a()(o))),"object"==(void 0===o?"undefined":a()(o))&&(console.log("여기 들어오나"),o=o.toISOString().split("T")[0]),i.a.get(l+o).then(function(t){e.toDoItems=t.data.map(function(t){return t.data}),e.nextToDoItems=[],console.log("한번 더 체크 dateTime: "+o)}).catch(function(t){console.log("error : ",t)})},createToDo:function(){event.preventDefault();var t=this;t.newToDoItemRequest.title&&(console.log("왜 수정이 안되니"),i.a.post(l+"create",t.newToDoItemRequest).then(function(e){console.log(e),t.newToDoItemRequest={},t.nextToDoItems=[],t.initToDoList(t.myDate)}).catch(function(t){console.log(t)}))},markDone:function(t){if(t){var e=this;t.done=!t.done,i.a.put(l,t).then(function(t){e.initToDoList(e.myDate)}).catch(function(t){console.log(t)})}},removeTodo:function(t){if(t){var e=this;i.a.post(l+"remove",t).then(function(t){e.initToDoList(e.myDate)}).catch(function(t){console.log(t)})}},clearAll:function(){var t=this;i.a.post(l+"clearAll",t.myDate).then(function(e){t.newToDoItemRequest={},t.initToDoList(t.myDate)}).catch(function(t){console.log(t)})},addNextToDo:function(t){void 0===t.selected&&(t.selected=!1);var e=this,o=e.nextToDoItems;o.find(function(e){return e.id===t.id});if(t.selected){var n=o.findIndex(function(e){return e.id===t.id});o.splice(n,1)}else o.push(t)},toNextDay:function(){var t=this;i.a.post(l+"toNextDay",t.nextToDoItems).then(function(e){t.nextToDoItems=[],t.initToDoList(t.myDate)}).catch(function(e){t.nextToDoItems=[],console.log(e)})},selectDate:function(t){var e=this;e.myDate=t.toISOString().split("T")[0],e.initToDoList(e.myDate)}},created:function(){this.initToDoList()}}},159:function(t,e,o){"use strict";var n=o(373),a=o.n(n),s=o(375),i=o(378),c=o(384),r=o(387),l=o(158);e.a={data:function(){return{todoItems:[]}},methods:{addOneItem:function(t){var e={completed:!1,item:t};localStorage.setItem(t,a()(e)),this.todoItems.push(e)},removeOneItem:function(t,e){localStorage.removeItem(t.item),this.todoItems.splice(e,1)},toggleOneItem:function(t,e){this.todoItems[e].completed=!this.todoItems[e].completed,localStorage.removeItem(t.item),localStorage.setItem(t.item,a()(t))},clearAllItems:function(){localStorage.clear(),this.todoItems=[]}},created:function(){if(localStorage.length>0)for(var t=0;t<localStorage.length;t++)"loglevel:webpack-dev-server"!==localStorage.key(t)&&(console.log(JSON.parse(localStorage.getItem(localStorage.key(t)))),this.todoItems.push(JSON.parse(localStorage.getItem(localStorage.key(t)))))},components:{TodoHeader:s.a,TodoInput:i.a,TodoList:c.a,TodoFooter:r.a,Calendar:l.a}}},160:function(t,e,o){"use strict";var n=o(380);e.a={data:function(){return{newTodoItem:"",showModal:!1}},methods:{addTodo:function(){""!==this.newTodoItem?(this.$emit("addTodoItem",this.newTodoItem),this.clearInput()):this.showModal=!this.showModal},clearInput:function(){this.newTodoItem=""}},components:{Modal:n.a}}},161:function(t,e,o){"use strict";e.a={}},162:function(t,e,o){"use strict";e.a={props:["propsdata"],methods:{removeTodo:function(t,e){this.$emit("removeItem",t,e)},toggleComplete:function(t,e){this.$emit("toggleItem",t,e)}}}},163:function(t,e,o){"use strict";e.a={methods:{clearTodo:function(){this.$emit("clearAll")}}}},303:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=o(48),a=o(306),s=o(310),i=o(391),c=o(426),r=(o.n(c),o(427));o.n(r);n.default.use(i.a),n.default.config.productionTip=!1,new n.default({el:"#app",router:s.a,template:"<App/>",components:{App:a.a},render:function(t){return t(a.a)}})},306:function(t,e,o){"use strict";function n(t){o(307)}var a=o(137),s=o(309),i=o(24),c=n,r=i(a.a,s.a,!1,c,null,null);e.a=r.exports},307:function(t,e){},309:function(t,e,o){"use strict";var n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{attrs:{id:"app"}},[t._m(0),t._v(" "),o("main",[o("router-view")],1)])},a=[function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("header",[o("span",[t._v("Vue.js PWA")])])}],s={render:n,staticRenderFns:a};e.a=s},310:function(t,e,o){"use strict";var n=o(48),a=o(311),s=o(312),i=o(371);n.default.use(a.a),e.a=new a.a({mode:"history",routes:[{path:"/",component:s.a},{path:"/todoList",component:i.a}]})},312:function(t,e,o){"use strict";function n(t){o(313)}var a=o(138),s=o(370),i=o(24),c=n,r=i(a.a,s.a,!1,c,null,null);e.a=r.exports},313:function(t,e){},370:function(t,e,o){"use strict";var n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"hello"},[o("b-card",{staticClass:"mb-2",staticStyle:{"max-width":"40rem",margin:"auto","margin-top":"10vh"},attrs:{header:"ToDoList","border-variant":"info",align:"left"}},[o("b-form-group",{attrs:{id:"to-do-input"}},[o("b-container",{attrs:{fluid:""}},[o("VueDatepicker",{on:{selected:t.selectDate},model:{value:t.myDate,callback:function(e){t.myDate=e},expression:"myDate"}}),t._v(" "),o("b-row",{staticClass:"my-1"},[o("b-col",{attrs:{sm:"10"}},[o("b-form-input",{attrs:{type:"text",placeholder:"새 할 일을 적으세요."},on:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.createToDo(e)}},model:{value:t.newToDoItemRequest.title,callback:function(e){t.$set(t.newToDoItemRequest,"title",e)},expression:"newToDoItemRequest.title"}})],1),t._v(" "),o("b-col",{attrs:{sm:"2"}},[o("b-button",{attrs:{variant:"outline-primary"},on:{click:t.createToDo}},[t._v("추가")])],1)],1)],1)],1),t._v(" "),t.toDoItems&&t.toDoItems.length?o("b-list-group",t._l(t.toDoItems,function(e){return o("b-list-group-item",{key:e.id,staticStyle:{display:"flex"},attrs:{data:e.id}},[o("b-form-checkbox",{on:{change:function(o){return t.addNextToDo(e)}},model:{value:e.selected,callback:function(o){t.$set(e,"selected",o)},expression:"toDoItem.selected"}}),t._v(" "),e.done?o("span",{staticStyle:{"text-decoration":"line-through",color:"#D3D3D3"}},[t._v("\n          "+t._s(e.title))]):o("span",[t._v(t._s(e.title))]),t._v(" "),o("span",{staticClass:"btn"},[o("i",{staticClass:"completeBtn fas fa-check",on:{click:function(o){return t.markDone(e)}},model:{value:e.done,callback:function(o){t.$set(e,"done",o)},expression:"toDoItem.done"}}),t._v(" "),o("i",{staticClass:"removeBtn fas fa-trash-alt",on:{click:function(o){return t.removeTodo(e)}}})])],1)}),1):t._e(),t._v(" "),o("b-card-footer",[o("b-button-group",{staticClass:"Footer"},[o("b-button",{staticClass:"clearBtn",attrs:{variant:"danger"},on:{click:t.clearAll}},[t._v("Clear All")])],1),t._v(" "),o("span",{staticClass:"footerBtn"},[o("i",{staticClass:"nextBtn fas fa-angle-double-right",on:{click:function(e){return t.toNextDay()}}})])],1)],1)],1)},a=[],s={render:n,staticRenderFns:a};e.a=s},371:function(t,e,o){"use strict";function n(t){o(372)}var a=o(159),s=o(390),i=o(24),c=n,r=i(a.a,s.a,!1,c,null,null);e.a=r.exports},372:function(t,e){},375:function(t,e,o){"use strict";function n(t){o(376)}var a=o(377),s=o(24),i=n,c=s(null,a.a,!1,i,"data-v-87add26e",null);e.a=c.exports},376:function(t,e){},377:function(t,e,o){"use strict";var n=function(){var t=this,e=t.$createElement;t._self._c;return t._m(0)},a=[function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("header",[o("h1",[t._v("TODO it!")])])}],s={render:n,staticRenderFns:a};e.a=s},378:function(t,e,o){"use strict";function n(t){o(379)}var a=o(160),s=o(383),i=o(24),c=n,r=i(a.a,s.a,!1,c,null,null);e.a=r.exports},379:function(t,e){},380:function(t,e,o){"use strict";function n(t){o(381)}var a=o(161),s=o(382),i=o(24),c=n,r=i(a.a,s.a,!1,c,null,null);e.a=r.exports},381:function(t,e){},382:function(t,e,o){"use strict";var n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("transition",{attrs:{name:"modal"}},[o("div",{staticClass:"modal-mask"},[o("div",{staticClass:"modal-wrapper"},[o("div",{staticClass:"modal-container"},[o("div",{staticClass:"modal-header"},[t._t("header",[t._v("\n            default header\n          ")])],2),t._v(" "),o("div",{staticClass:"modal-body"},[t._t("body",[t._v("\n            default body\n          ")])],2),t._v(" "),o("div",{staticClass:"modal-footer"},[t._t("footer",[t._v("\n            default body\n          ")])],2)])])])])},a=[],s={render:n,staticRenderFns:a};e.a=s},383:function(t,e,o){"use strict";var n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"inputBox shadow"},[o("input",{directives:[{name:"model",rawName:"v-model",value:t.newTodoItem,expression:"newTodoItem"}],attrs:{type:"text"},domProps:{value:t.newTodoItem},on:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.addTodo(e)},input:function(e){e.target.composing||(t.newTodoItem=e.target.value)}}}),t._v(" "),o("span",{staticClass:"addContainer",on:{click:t.addTodo}},[o("i",{staticClass:"fas fa-plus addBtn"})]),t._v(" "),t.showModal?o("Modal",{on:{close:function(e){t.showModal=!1}}},[o("h3",{attrs:{slot:"header"},slot:"header"},[t._v("\n      경고!\n      "),o("i",{staticClass:"closeModalBtn fas fa-times",on:{click:function(e){t.showModal=!1}}})]),t._v(" "),o("div",{attrs:{slot:"body"},slot:"body"},[t._v("\n      추가할 내용을 입력하세요.\n    ")]),t._v(" "),o("div",{attrs:{slot:"footer"},slot:"footer"},[t._v("\n      copyright: 가익가익\n    ")])]):t._e()],1)},a=[],s={render:n,staticRenderFns:a};e.a=s},384:function(t,e,o){"use strict";function n(t){o(385)}var a=o(162),s=o(386),i=o(24),c=n,r=i(a.a,s.a,!1,c,"data-v-dc6b5ee8",null);e.a=r.exports},385:function(t,e){},386:function(t,e,o){"use strict";var n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("section",[o("transition-group",{attrs:{name:"list",tag:"ul"}},t._l(t.propsdata,function(e,n){return o("li",{key:e.item,staticClass:"shadow"},[o("i",{staticClass:"checkBtn fas fa-check",class:{checkBtnCompleted:e.completed},on:{click:function(o){return t.toggleComplete(e,n)}}}),t._v(" "),o("span",{class:{textCompleted:e.completed}},[t._v(t._s(e.item))]),t._v(" "),o("span",{staticClass:"removeBtn",on:{click:function(o){return t.removeTodo(e,n)}}},[o("i",{staticClass:"removeBtn fas fa-trash-alt"})])])}),0)],1)},a=[],s={render:n,staticRenderFns:a};e.a=s},387:function(t,e,o){"use strict";function n(t){o(388)}var a=o(163),s=o(389),i=o(24),c=n,r=i(a.a,s.a,!1,c,null,null);e.a=r.exports},388:function(t,e){},389:function(t,e,o){"use strict";var n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"clearAllContainer"},[o("span",{staticClass:"clearAllBtn",on:{click:t.clearTodo}},[t._v("Clear All")])])},a=[],s={render:n,staticRenderFns:a};e.a=s},390:function(t,e,o){"use strict";var n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{attrs:{id:"app"}},[o("TodoHeader"),t._v(" "),o("TodoInput",{on:{addTodoItem:t.addOneItem}}),t._v(" "),o("TodoList",{attrs:{propsdata:t.todoItems},on:{removeItem:t.removeOneItem,toggleItem:t.toggleOneItem}}),t._v(" "),o("TodoFooter",{on:{clearAll:t.clearAllItems}}),t._v(" "),o("Calendar",[t._v("Calendar")])],1)},a=[],s={render:n,staticRenderFns:a};e.a=s},426:function(t,e){},427:function(t,e){}},[303]);
//# sourceMappingURL=app.bba59f080466aac599a3.js.map