import { useState } from "react";
import "./App.css";

type ItemId = `${string}-${string}-${string}-${string}-${string}`;

interface Item {
  id: ItemId;
  timestamp: number;
  text: string;
}

function App() {
  const [items, setItems] = useState<Item[]>([]);
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

  const handleRemoveItem = (id: ItemId) => () => {
    setItems((prevElements) => {
      return prevElements.filter((currentItem) => currentItem.id !== id);
    });
  };

  return (
    <main>
      <aside>
        <h1>Add Element to a list app</h1>
        <form onSubmit={handleSubmit} aria-label="Add and remove Items">
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
        {items.length === 0 ? (
          <strong>There are not elements</strong>
        ) : (
          <ul>
            {items.map((item) => (
              <li key={item.id}>
                {item.text}
                <button
                  onClick={handleRemoveItem(item.id)}
                  // onClick={() => {
                  //   setItems((prevElements) => {
                  //     return prevElements.filter(
                  //       (currentItem) => currentItem.id !== item.id,
                  //     );
                  //   });
                  // }}
                >
                  x
                </button>
              </li>
            ))}
          </ul>
        )}
      </section>
    </main>
  );
}

export default App;
