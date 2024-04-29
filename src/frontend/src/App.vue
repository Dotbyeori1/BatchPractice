<template>
  <div id="app">
    <DashBoardComponent />
  </div>
</template>

<script>
import DashBoardComponent from "@/components/DashBoardComponent.vue";
export default {
  name: 'App',
  components: {DashBoardComponent}
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>