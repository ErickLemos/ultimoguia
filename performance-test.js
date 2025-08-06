import http from 'k6/http';
import { check } from 'k6';

export const options = {
    vus: 100,
    iterations: 100_000,
};

const params = {
    headers: {
        'Content-Type': 'application/json',
    },
};

export default function () {
    const uniqueId = `${__VU}-${__ITER}`;
    const payload = JSON.stringify({
        titulo: __ITER,
        conteudo: `teste conteudo ${uniqueId}`
    });
    const res = http.post(`http://localhost:30000/notas`, payload, params);
    check(res, {
        'status da requisição é 200': (r) => r.status === 200,
    });
}