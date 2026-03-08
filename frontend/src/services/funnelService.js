const API_BASE = "http://localhost:8080/api";

export const funnelService = {
  // Get UI Config
  getFunnelConfig: (slug) =>
    fetch(`${API_BASE}/funnels/${slug}`).then(res => res.json()),

  // Get Users
  getUsers: () =>
    fetch(`${API_BASE}/users`).then(res => res.json()),

  // Submit Claim
  postClaim: (data) =>
    fetch(`${API_BASE}/claims`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    }).then(res => res.json())
};