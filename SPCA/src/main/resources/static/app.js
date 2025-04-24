async function api(url, opts = {}) {
	const res = await fetch(url, {
		credentials: 'include',
		headers: { 'Content-Type': 'application/json' },
		...opts
	});
	if (!res.ok) throw res;
	try { return await res.json(); }
	catch { return res; }
}

api('/api/session')
	.then(user => { currentCustomerId = user.id; })
	.catch(() => { location.href = '/'; });