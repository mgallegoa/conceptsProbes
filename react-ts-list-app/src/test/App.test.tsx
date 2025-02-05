import { describe, test, expect, afterEach } from "vitest";
import { render, screen, cleanup } from "@testing-library/react";
import { userEvent } from "@testing-library/user-event";

import App from "../App";

describe("<App />", () => {
  afterEach(() => {
    cleanup();
  });
  test("Render the page, first way", () => {
    render(<App />);
    // screen.debug();
    expect(screen.getByText(/Add Element/i)).toBeDefined();
    expect(screen.getByText("Add Element to a list app")).toBeDefined();
  });

  test("Render the page, second way", () => {
    const { getByText } = render(<App />);
    // screen.debug();
    expect(getByText("Add Element to a list app")).toBeDefined();
  });

  test("End to End test: Should add items and remove them", async () => {
    const user = userEvent.setup();
    const textEmpty = "There are not elements";
    render(<App />);

    const input = screen.getByRole("textbox");
    expect(input).toBeDefined();

    const form = screen.getByRole("form");
    expect(form).toBeDefined();

    const button = form.querySelector("button");
    expect(button).toBeDefined();

    // Test add element
    const customText = "Manuel first item";
    await user.type(input, customText);
    await user.click(button!);

    // second element
    const randomText = crypto.randomUUID();
    await user.type(input, randomText);
    await user.click(button!);

    const list = screen.getByRole("list");
    expect(list).toBeDefined();

    expect(list.childNodes.length).toBe(2);

    // Test delete the element
    const item = screen.getByText(randomText);
    expect(item).toBeDefined();
    const removeButton = item.querySelector("button");
    expect(removeButton).toBeDefined();

    await user.click(removeButton!);
    expect(list.childNodes.length).toBe(1);

    // Test delete the last element
    const item2 = screen.getByText(customText);
    expect(item2).toBeDefined();
    const removeButton2 = item2.querySelector("button");
    expect(removeButton2).toBeDefined();

    await user.click(removeButton2!);
    const screenText = screen.getByText(textEmpty).textContent;
    expect(screenText).toEqual(textEmpty);
  });
});
