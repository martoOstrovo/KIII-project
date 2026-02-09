import { useEffect, useState } from "react";
import { api } from "./api";

export default function App() {

    const [items, setItems] = useState([]);
    const [name, setName] = useState("");
    const [desc, setDesc] = useState("");
    const [editingId, setEditingId] = useState(null);

    async function load() {
        const data = await api.list();
        setItems(data);
    }

    useEffect(() => {
        load();
    }, []);

    async function submit(e) {
        e.preventDefault();

        const dto = { itemName: name, itemDescription: desc };

        if (editingId === null) {
            await api.create(dto);
        } else {
            await api.update(editingId, dto);
            setEditingId(null);
        }

        setName("");
        setDesc("");
        load();
    }

    function edit(item) {
        setEditingId(item.itemId);
        setName(item.itemName);
        setDesc(item.itemDescription || "");
    }

    async function remove(id) {
        await api.remove(id);
        load();
    }

    return (
        <div className="app-container">
            <div className="card">

                <h1>Todo App</h1>

                <form onSubmit={submit} className="form">
                    <input
                        placeholder="Name"
                        value={name}
                        onChange={e => setName(e.target.value)}
                        required
                    />

                    <input
                        placeholder="Description"
                        value={desc}
                        onChange={e => setDesc(e.target.value)}
                    />

                    <button type="submit">
                        {editingId === null ? "Add" : "Save"}
                    </button>
                </form>

                <ul className="list">
                    {items.map(item => (
                        <li key={item.itemId} className="list-item">
                            <div>
                                <b>{item.itemName}</b>
                                <div className="desc">{item.itemDescription}</div>
                            </div>

                            <div className="actions">
                                <button onClick={() => edit(item)}>Edit</button>
                                <button onClick={() => remove(item.itemId)}>Delete</button>
                            </div>
                        </li>
                    ))}
                </ul>

            </div>
        </div>
    );

}
