import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from "../router"

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: null,
    usuario: null,
    conversaAtual: null,
    listaConversas: null,
  },
  mutations: {
    setUsuario(state, usuario) {
      state.usuario = usuario;
    },
    setToken(state, token) {
      state.token = token;
    },
    setConversaAtual(state, conversa) {
      state.conversaAtual = conversa;
    },
    setListaConversas(state, conversas) {
      state.listaConversas = conversas
    },
    getConversaAtual(state) {
      return state.conversaAtual;
    },
    getListaConversas(state) {
      return state.listaConversas;
    },
    logout(state) {
      state.token = null;
      state.usuario = null;
    }
  },
  actions: {
    login(context, { usuario, senha }) {
      axios
        .post("login", {
          nome: usuario,
          senha: senha
        })
        .then(res => {
          console.log(res);
          context.commit('setUsuario', usuario);
          context.commit('setToken', res.data.token);
          router.push('/');
        })
        .catch(error => console.log(error));
    },
    getListaConversas(context) {
      axios
        .get('listar_por_usuario', {})
    }
  },
  modules: {}
});