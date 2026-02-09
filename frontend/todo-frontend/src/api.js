const API = "/api/todos";

async function request(path = "", options = {}) {
    const res = await fetch(API + path, {
        headers: { "Content-Type": "application/json" },
        ...options
    });

    if (res.status === 204) return null;

    const data = await res.json();

    if (!res.ok) {
        throw new Error(data.message || "Request failed");
    }

    return data;
}

export const api = {
    list: () => request(),
    create: (dto) => request("", {
        method: "POST",
        body: JSON.stringify(dto)
    }),
    update: (id, dto) => request(`/${id}`, {
        method: "PUT",
        body: JSON.stringify(dto)
    }),
    remove: (id) => request(`/${id}`, {
        method: "DELETE"
    })
};
