<template>
  <div>
    <div @click="$router.go(-1)">
    木棍
    </div>
  <div>
    <div @click="pick">捨取</div>

    <div @click="$router.go(-1)">返回</div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'Artifact',
  data () {
    return {
      id: ''
    }
  },
  created() {
    this.id = this.$route.params.id;
     //this.$socket.sendObj({type: 'status'});
     
    this.$options.sockets.onmessage = (data) => {
      data = JSON.parse(data.data);
      if (data.type == 'pick') {
        this.$router.go(-1);
      }
    } 
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

</style>
