<template>
  <div id="app">
    <router-view/>

    <div class="row message-window" v-show="logined">
      <div class="col-xs-12" id="message">
        <p v-for="m in messages">{{m}}</p>
      </div>
    </div>

    <div class="row footer" v-show="logined">
      <div class="col-xs-3" @click="$router.push({name: 'HelloWorld'})">首页</div>
      <div class="col-xs-3" @click="$router.push({name: 'Status'})">状态</div>
      <div class="col-xs-3" @click="$router.push({name: 'Bag'})">背包</div>
      <div class="col-xs-3">任务</div>
    </div>
  </div>
</template>

<script>

import HelloWorld from "@/components/HelloWorld";

export default {
  name: 'App',
  data() {
    return {
      logined: false,
      messages: []
    }
  },
  mounted: function() {
    //alert('hi')
    //this.$socket.connect();
    this.$options.sockets.onmessage = (data) => {
      data = JSON.parse(data.data);
      console.log('app, data: ')
      console.log(data)
      console.log('data.type: ', data.type)
      if (data.type == 'chat') {
        this.messages.push(data.content);
        if (this.messages.length > 1000) {
            this.messages.slice(0, 400);
        }
        console.log('pushed...')
        $('#message').scrollTop(999999)
      } else if (data.type == 'login') {
        this.logined = false;
        this.$router.push('/login')
      } else if (data.type == 'logined') {
        this.logined = true;
        this.$router.push('/')
      }
    }
    
    //this.$socket.sendObj({name: 'kevin'})
    //this.$socket.sendObj({age: 21})
  }

}
</script>

<style>
#message {
  text-align: left;
  height: 100px;
  border: 1px solid #ccc;
  overflow-y: scroll;
  position: absolute;
  width: 100%;
  left: 0;
  bottom: 50px;
  background-color: #444;
  color: white;
}

#app {
  font-family: 'KaiTi', 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  
  
}

.footer {
  position: fixed;
  width: 100%;
  left: 0;
  bottom: 0;
  height: 50px;
}
.footer div {
  height: 50px;
  line-height: 50px;
  font-size: 20px;
  font-family: KaiTi;
}
</style>
