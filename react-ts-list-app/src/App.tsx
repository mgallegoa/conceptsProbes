import "./App.css";
import { Item } from "./components/Item";
import { useItems } from "./hooks/useItems";
import { useSEO } from "./hooks/useSEO";

export type ItemId = `${string}-${string}-${string}-${string}-${string}`;

export interface Item {
  id: ItemId;
  timestamp: number;
  text: string;
}

function App() {
  const { items, addItem, removeItem } = useItems();
  useSEO({
    title: `${items.length} React + typescript + vitest + Hooks`,
    description: "Add and delete elements from a list",
  });
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
    <>
      <header>
        <nav className="navbar navbar-light bg-light">
          <a
            href="https://github.com/mgallegoa/conceptsProbes/blob/master/react-ts-list-app/README.md"
            className="navbar-brand"
          >
            Go to documentation
          </a>
          <img
            src="https://www.gravatar.com/avatar/dd43ba3e67fd9efdb57c9b60b16c4306.jpg?s=80"
            className="rounded-circle"
          />
        </nav>
      </header>
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
            <strong className="list__empty">There are not elements</strong>
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
    </>
  );
}

export default App;
