<template>
  <div>
    <div @click="$router.go(-1)">
      登陆
      <a href="#" @click="login">login</a>
    </div>
  <div>
    <ul class="bag">
    </ul>
  </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
    }
  },
  created() {
  },
  methods: {
    login: function() {
      this.$socket.sendObj({type: 'login'})
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
