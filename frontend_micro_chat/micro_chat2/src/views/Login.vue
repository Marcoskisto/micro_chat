<template>
  <form @submit.prevent="auth">
    <h2>Login</h2>
    <div class="form-group">
      <label for="username">Usuário</label>
      <input type="text" id="username" class="form-control" 
        required autofocus v-model="nome">
    </div>
    <div class="form-group">
      <label for="inputPassword">Senha</label>
      <input type="password" id="inputPassword" class="form-control" 
        required v-model="senha">
    </div>
    <button class="btn btn-lg btn-primary btn-block" 
      type="submit">Ok</button>
  </form>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { mapActions } from "vuex";
import store from '@/store';

@Component({
  name: "Login",
  }
})
export default class Login extends Vue {
  public nome: string = "";
  public senha: string = "";
  methods: {

    auth(): void {
      ...mapActions(['login']);
      this.login({ usuario: this.nome, senha: this.senha });
    };
  };
};
</script>