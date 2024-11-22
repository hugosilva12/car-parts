FROM node:16-alpine
ARG REACT_API_PATH=http://localhost:9191/api/

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 3000
# required for docker desktop port mapping

CMD ["npm", "start"]