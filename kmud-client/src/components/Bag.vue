<template>
  <div>
    <div @click="$router.go(-1)">
      背包
    </div>
  <div>
    <ul class="bag">
      <li v-for="a in artifacts">{{a.name}}</li>
    </ul>
  </div>
  </div>
</template>

<script>
export default {
  name: 'Bag',
  data () {
    return {
      artifacts: ''
    }
  },
  created() {
     //this.$socket.sendObj({type: 'status'});
     
    this.$options.sockets.onmessage = (data) => {
      data = JSON.parse(data.data);
      if (data.type == 'bag') {
        this.artifacts = data.artifacts;
      }
    } 
    this.$socket.sendObj({type: 'bag'});
  },
  methods: {
    pick: function() {
      this.$socket.sendObj({type: 'pick', id: this.id});
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.bag {

}
.bag li {
  list-style-type: none;
  float: left;
  width: 25%;
  border: 1px solid #ccc;
  height: 50px;
  line-height: 50px;
}
</style>
