<template>
<div>
  <nav class="header navbar justify-content-start shadow-lg p-3 mb-5">
      <ul class="nav">
        <li class="nav-item active"
          v-for="routes in links" :key="routes.id">  
          <router-link class="nav-link" :to="routes.path">
            <span class="link">{{routes.name}}</span>
          </router-link>
        </li>
      </ul> 
  </nav>
</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class Header extends Vue {
  data() {
    return {
      links: [
        {
          id: 0,
          name: "Home",
          path: "/",
        },
        {
          id: 1,
          name: "About",
          path: "/about",
        },
        {
          id: 2,
          name: "Chat",
          path: "/Chat",
        },
      ],
    };
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.header {
  margin:auto;
  width: 100%;
  margin-top: 20px;
  background-color: transparent;
}

.link{
  font-size: 110%;
  color:white;
}
routerLinklink{
  display: block;
  color: white;
}
div{

}
</style>
