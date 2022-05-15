<template>
<div class="leftBar">
  <div class="conversa" v-for="cnv in conversas" :key="cnv.id">
  <b-list-group-item class="d-flex align-items-center bar">
    <b-avatar class="mr-3"></b-avatar>
    <span class="mr-auto">{{cnv.assunto}}</span>
    <b-badge>5</b-badge>
  </b-list-group-item>
  </div>
</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class LeftBar extends Vue {
  data(){
    return {
      conversas:[
        {id: 0, assunto: "conversa 1"},
        {id: 1, assunto: "escola"},
        {id: 2, assunto: "Maria"},
        {id: 0, assunto: "conversa 1"},
        {id: 1, assunto: "escola"},
        {id: 2, assunto: "Maria"}
      ]
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.bar{
}
.conversa{
  width:15%;
}


</style>
