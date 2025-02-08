FROM node:22

WORKDIR /app/react-ts-list-app

COPY package.json pnpm-lock.yaml ./
RUN npm install -g pnpm@latest-10 && pnpm install

# Copy the application
COPY . .

EXPOSE 5173

# This app run in the internal port 5174, go to README.md
CMD ["pnpm", "run", "dev"]


