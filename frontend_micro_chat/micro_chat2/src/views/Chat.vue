<template>
  <div class="chat">
    <leftBar></leftBar>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import LeftBar from '@/components/LeftBar.vue';
@Component({
  components: {
    LeftBar,
  },
})
export default class Chat extends Vue {}
</script>
