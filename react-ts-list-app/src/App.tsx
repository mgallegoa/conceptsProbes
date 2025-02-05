import "./App.css";
import { Item } from "./components/Item";
import { useItems } from "./hooks/useItems";

export type ItemId = `${string}-${string}-${string}-${string}-${string}`;

export interface Item {
  id: ItemId;
  timestamp: number;
  text: string;
}

function App() {
  const { items, addItem, removeItem } = useItems();
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const { elements } = event.currentTarget;
    // Strategy 1: not correct
    // const input = elements.namedItem('item') as HTMLInputElement

    // Strategy 2: recommended
    const input = elements.namedItem("item");
    const isInput = input instanceof HTMLInputElement;
    if (!isInput || isInput == null) return;
    addItem(input.value);
    input.value = "";
  };
  const handleRemoveItem = (id: ItemId) => () => {
    removeItem(id);
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
              <Item
                {...item}
                handleClick={handleRemoveItem(item.id)}
                key={item.id}
              />
            ))}
          </ul>
        )}
      </section>
    </main>
  );
}

export default App;
