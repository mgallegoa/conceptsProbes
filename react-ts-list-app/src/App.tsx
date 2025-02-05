import { useState } from "react";
import "./App.css";

interface Item {
  id: `${string}-${string}-${string}-${string}-${string}`;
  timestamp: number;
  text: string;
}

const INITIAL_ITEMS: Array<Item> = [
  {
    id: crypto.randomUUID(),
    timestamp: Date.now(),
    text: "Item 1",
  },
  {
    id: crypto.randomUUID(),
    timestamp: Date.now(),
    text: "Item 2",
  },
];

function App() {
  const [items, setItems] = useState(INITIAL_ITEMS);
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const { elements } = event.currentTarget;
    // Strategy 1: not correct
    // const input = elements.namedItem('item') as HTMLInputElement

    // Strategy 2: recommended
    const input = elements.namedItem("item");
    const isInput = input instanceof HTMLInputElement;
    if (!isInput || isInput == null) return;
    const newItem: Item = {
      id: crypto.randomUUID(),
      timestamp: Date.now(),
      text: input.value,
    };
    setItems((prevItems) => {
      return [...prevItems, newItem];
    });
    input.value = "";
  };
  return (
    <main>
      <aside>
        <h1>Add Element to a list app</h1>
        <form onSubmit={handleSubmit}>
          <label>
            Element to add:
            <input
              name="item"
              required
              type="text"
              placeholder="Write item to add"
            />
          </label>
          <button type="submit">Add Item</button>
        </form>
      </aside>
      <section>
        <ul>
          {items.map((item) => (
            <li key={item.id}>
              {item.text}
              <button
                onClick={() => {
                  setItems((prevElements) => {
                    return prevElements.filter(
                      (currentItem) => currentItem.id !== item.id,
                    );
                  });
                }}
              >
                x
              </button>
            </li>
          ))}
        </ul>
      </section>
    </main>
  );
}

export default App;
