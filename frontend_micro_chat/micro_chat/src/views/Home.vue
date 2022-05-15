<template>
  <div class="home">

    <HelloWorld msg="Welcome to µchat"/>

  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import Header from '@/components/Header.vue';
@Component({
  components: {
    HelloWorld,
    Header,
  },
})
export default class Home extends Vue {}
</script>
