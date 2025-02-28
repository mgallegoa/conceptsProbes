FROM node:22-alpine3.20

WORKDIR /app/rest-api-node

COPY package.json ./
RUN npm install -g pnpm@latest-10 && pnpm install

COPY . .

CMD ["pnpm", "run", "dev"]


