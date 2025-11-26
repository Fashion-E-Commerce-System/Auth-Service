import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 1,
    duration: '10s',
};

const credentials = {
    username: 'testuser',
    password: 'password',
};

export default function () {
    // 1. Login
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

    // 2. Refresh (쿠키 방식)
    const jar = http.cookieJar();
    jar.set('http://localhost:8080', 'refreshToken', refreshToken);

    const refreshRes = http.post('http://localhost:8080/auth/refresh', null, {
        headers: { 'Content-Type': 'application/json' },
        cookies: jar.cookiesForURL('http://localhost:8080'),
    });

    check(refreshRes, {
        'token refresh successful': (r) => r.status === 200,
    });

    if (refreshRes.status === 200) {
        accessToken = refreshRes.json('accessToken');
        console.log('🔄 Access token refreshed:');
    } else {
        console.error(`❌ Failed to refresh token: ${refreshRes.status} ${refreshRes.body}`);
    }

    sleep(1);

    // 3. Logout
    const logoutRes = http.post('http://localhost:8080/auth/logout', null, {
        headers: {
            'Authorization': `Bearer ${accessToken}`,
            'Content-Type': 'application/json',
        },
        cookies: jar.cookiesForURL('http://localhost:8080'),
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