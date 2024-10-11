# Dockerfile for Angular UI

# Use the official Node.js image for the build stage
FROM node:18 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

  # Use the official NGINX image for serving the application
FROM nginx:alpine
COPY --from=build /app/dist/customer-account-manager-ui/browser /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
