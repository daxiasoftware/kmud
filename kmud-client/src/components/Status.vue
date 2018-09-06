<template>
  <div>
    经验: <span>{{exp}}</span>
    <p></p>
    生命: <span>{{hp}}</span>
  </div>
</template>

<script>
export default {
  name: 'Status',
  data () {
    return {
      hp: '',
      exp: ''
    }
  },
  created() {
    // this.$options.sockets.onmessage = (data) => console.log('hello world data', data);
     this.$socket.sendObj({type: 'status'});
    //this.$socket.sendObj({type: 'room'});
    this.$options.sockets.onmessage = (data) => {
      data = JSON.parse(data.data);
      if (data.type == 'status') {
        this.hp = data.hp;
        this.exp = data.exp;
      }
    }
  },
  methods: {
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
