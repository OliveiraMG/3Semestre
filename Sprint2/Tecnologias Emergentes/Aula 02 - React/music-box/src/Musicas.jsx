import React, { useEffect } from "react";
import api from "./api";
import Menu from "./Menu"
import ItemMusica from "./ItemMusica";

import { useState} from "react";

function Musicas() {

    const [musicas, setMusicas] = useState([]);
    // criando state com valor de um vetor vazio;
    function listar() {
        api.get()
            .then((respostaObtida) => {
                // cairá aqui se a requisição for realizada;
                console.log(respostaObtida);
                // objeto que representa a resposta enviada pela API;
                console.log(respostaObtida.status);
                // vendo status da resposta (OK - 200);
                console.log(respostaObtida.data);
                // vendo os dados da resposta (data: []);
                setMusicas(respostaObtida.data)
                // setando "musicas" com os mesmos dados recebidos pela resposta da requisição;
            })
            .catch((erroOcorrido) => { // cairá aqui se houver algum erro durante a requisição
                console.log(erroOcorrido);
            })
    }

    useEffect(() => {
        listar();
    }, [])

    return (
        <>
            <Menu />
            <div class="container">
                <div class="filter">
                    <button class="btn">Adicionar</button>
                </div>
            </div>
            <div class="container">
                <div class="music-boxes">
                    {musicas.map((item) => <ItemMusica nome={item.nome} artista={item.artista} genero={item.genero} ano={item.ano} />)}
                </div>
            </div>
        </>
    );
}

export default Musicas;