import http from 'k6/http';
import { check, sleep } from 'k6';

let accessToken = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmdAbmF2ZXIuY29tIiwiaWF0IjoxNzYyNTE0OTEzLCJleHAiOjE3NjI1MTUyMTN9.K9N_C-HQgz3IuYcwcI4izRLGXwAPeC0pKszAr73C2Ts';
const refreshToken = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmdAbmF2ZXIuY29tIiwiaWF0IjoxNzYyNTE0OTEzLCJleHAiOjE3NjI2MDEzMTN9.0MCDy6chQISU1eVslBioa2jaxONv5yyIlQcU3LDVHzg';

export let options = {
    vus: 10,
    duration: '30s',
};

function refreshAccessToken() {
    const res = http.post('http://localhost:8080/auth/refresh', JSON.stringify({
        refreshToken: refreshToken,
    }), {
        headers: { 'Content-Type': 'application/json' },
    });

    if (res.status === 200) {
        const json = res.json();
        accessToken = json.accessToken;
        console.log('🔄 Access token refreshed');
    } else {
        console.error('❌ Failed to refresh token');
    }
}

export default function () {
    let res = http.get('http://localhost:8080/api/products/', {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });

    if (res.status === 401) {
        refreshAccessToken();

        // 재시도
        res = http.get('http://localhost:8080/api/products/', {
            headers: {
                Authorization: `Bearer ${accessToken}`,
            },
        });
    }

    check(res, {
        'status is 200': (r) => r.status === 200,
    });

    sleep(1);
}