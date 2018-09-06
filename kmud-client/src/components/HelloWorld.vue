<template>
  <div class="hello">

    <p>{{description}}</p>

    <div v-for="e in exits" v-if="e.direction == 'N'">
        <h3 class="exit" @click="goRoom(e.toRoomId)">{{e.name}}</h3>
        <div class="room-path-n">&nbsp;</div>
    </div>
    <div>
      <h2>{{name}}</h2>
    </div>
    <div v-for="e in exits" v-if="e.direction == 'S'">
        <div class="room-path-n">&nbsp;</div>
        <h3 class="exit" @click="goRoom(e.toRoomId)">{{e.name}}</h3>
    </div>

    <div class="row">
      <div class="col-xs-12">
        这个房间的物品有：<a href="javascript:void(0);" v-for="a in artifacts" @click="$router.push({name: 'Artifact', params: {id: a.id}})">{{a.name}}</a>
      </div>
    </div>
    
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      name: '',
      description: '',
      exits: [],
      artifacts: []
    }
  },
  created: function() {

      console.log('onopen...')
      this.$options.sockets.onmessage = (data) => {
      console.log('HelloWorld, data: ', data)
      data = JSON.parse(data.data);
      console.log('app, data: ')
      console.log(data)
      console.log('data.type: ', data.type)
      if (data.type == 'room') {
        this.name = data.name;
        this.description = data.description;
        this.exits = data.exits;
        this.artifacts = data.artifacts;
      }
    }
    this.$socket.sendObj({type: 'room'});
    
  //  this.$socket.sendObj({type: 'room'});
  },
  methods: {
    fk: function() {
      console.log('fk???');
    },
    goRoom: function(toRoomId) {
      this.$socket.sendObj({type: 'goroom', toRoomId: toRoomId});
    }
  },
  VueNativeSock: {
    SOCKET_ONOPEN: function() {
      console.log('onopen.......')
      alert('onopen...')
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.exit {
  color: blue;
}
.room-path-n {
  height: 50px;
  width: 10px;
  background-color: #000;
  margin-left: 50%;
  
}
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
  color: #42b983;
}
</style>
