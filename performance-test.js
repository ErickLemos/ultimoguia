import http from 'k6/http';
import { check } from 'k6';

export const options = {
    iterations: 100_000,
};

const params = {
    headers: {
        'Content-Type': 'application/json',
    },
};

let contador = 0
export default function () {
    const payload = JSON.stringify({
        titulo: `titulo ${contador}`,
        conteudo: `teste conteudo ${contador}`
    });

    const res = http.post('http://localhost:30000/notas', payload, params);
    check(res, {
        'status da requisição é 201 (Created)': (r) => r.status === 200,
    });
    contador++
}