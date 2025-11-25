import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 1,
    duration: '1s',
};

// Replace with actual user credentials
const credentials = {
    id: 'testuser',
    password: 'password',
};
export default function () {
    // 1. Login to get tokens
    const loginRes = http.post('http://localhost:8080/auth/login', JSON.stringify(credentials), {
        headers: { 'Content-Type': 'application/json' },
    });

    check(loginRes, {
        'login successful': (r) => r.status === 200,
    });

    if (loginRes.status !== 200) {
        console.error(`❌ Login failed: ${loginRes.status} ${loginRes.body}`);
        return;
    }

    let accessToken = loginRes.json('accessToken');
    const refreshToken = loginRes.json('refreshToken');
    console.log('✅ Login successful');

    sleep(1);

    // 2. Refresh the access token (Authorization 헤더로 전달)
    const refreshRes = http.post('http://localhost:8080/auth/refresh', null, {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${refreshToken}`,  // ✅ Refresh Token을 헤더로 보냄
        },
    });

    check(refreshRes, {
        'token refresh successful': (r) => r.status === 200,
    });

    if (refreshRes.status === 200) {
        accessToken = refreshRes.json('accessToken');
        console.log('🔄 Access token refreshed');
    } else {
        console.error(`❌ Failed to refresh token: ${refreshRes.status} ${refreshRes.body}`);
    }

    sleep(1);

    // 3. Logout (Access Token으로 인증)
    const logoutRes = http.post('http://localhost:8080/auth/logout', null, {
        headers: {
            'Authorization': `Bearer ${accessToken}`,
        },
    });

    check(logoutRes, {
        'logout successful': (r) => r.status === 200,
    });

    if (logoutRes.status === 200) {
        console.log('👋 Logout successful');
    } else {
        console.error(`❌ Logout failed: ${logoutRes.status} ${logoutRes.body}`);
    }

    sleep(1);
}
