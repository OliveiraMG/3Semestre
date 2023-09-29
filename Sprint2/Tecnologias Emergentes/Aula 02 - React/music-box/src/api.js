import axios from "axios";

const api = axios.create({
    baseURL:"https://6515fced09e3260018c95330.mockapi.io/musicas",
});

export default api;