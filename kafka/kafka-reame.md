# Configuration Kafka pour SosDoCta

## 📋 Description

Ce docker-compose configure un cluster Kafka avec :
- **Zookeeper** : Coordination du cluster
- **Kafka Broker** : Serveur de messages
- **Kafka Init** : Création automatique des topics au démarrage
- **Kafka UI** : Interface web de gestion (optionnelle)

## 🚀 Démarrage

### 1. Lancer le cluster Kafka

```bash
docker-compose -f docker-compose-kafka.yml up -d
```

### 2. Vérifier les logs

```bash
# Logs du broker Kafka
docker logs kafka-broker -f

# Logs de l'initialisation des topics
docker logs kafka-init

# Vérifier la santé des services
docker-compose -f docker-compose-kafka.yml ps
```

### 3. Accéder à l'interface Kafka UI

Ouvrez votre navigateur : `http://localhost:8080`

## 📊 Topics créés automatiquement

| Topic | Partitions | Rétention | Usage |
|-------|-----------|-----------|-------|
| `notification-topic` | 3 | 7 jours | Notifications générales |
| `email-topic` | 2 | 7 jours | Envoi d'emails |
| `sms-topic` | 2 | 7 jours | Envoi de SMS |
| `appointment-events` | 3 | 30 jours | Événements de rendez-vous |
| `payment-events` | 3 | 30 jours | Événements de paiement |
| `user-events` | 2 | 30 jours | Événements utilisateurs |
| `message-events` | 3 | 14 jours | Messages de la messagerie |
| `call-events` | 2 | 7 jours | Événements d'appels vidéo |
| `search-index-events` | 2 | 7 jours | Indexation pour la recherche |
| `audit-logs` | 3 | 90 jours | Logs d'audit |

## 🔧 Configuration des services

### Connecter vos microservices à Kafka

Ajoutez ces variables d'environnement à vos services dans votre docker-compose principal :

```yaml
environment:
  SPRING_KAFKA_BOOTSTRAP_SERVERS: kafka:29092
  # Pour les services Spring Boot
```

Pour les services Node.js :
```yaml
environment:
  KAFKA_BROKERS: kafka:29092
```

### Connecter les réseaux

Dans votre `docker-compose.yml` principal, ajoutez le réseau kafka-network :

```yaml
networks:
  kafka-network:
    external: true
    name: kafka-network
```

Puis ajoutez ce réseau à chaque service qui doit communiquer avec Kafka :

```yaml
services:
  notification:
    # ... votre config
    networks:
      - app-network
      - kafka-network  # Ajoutez cette ligne
```

## 📝 Commandes utiles

### Lister les topics
```bash
docker exec kafka-broker kafka-topics --list --bootstrap-server localhost:9092
```

### Voir les détails d'un topic
```bash
docker exec kafka-broker kafka-topics --describe --topic notification-topic --bootstrap-server localhost:9092
```

### Produire un message (test)
```bash
docker exec -it kafka-broker kafka-console-producer --topic notification-topic --bootstrap-server localhost:9092
```

### Consommer des messages (test)
```bash
docker exec -it kafka-broker kafka-console-consumer --topic notification-topic --from-beginning --bootstrap-server localhost:9092
```

### Créer un nouveau topic manuellement
```bash
docker exec kafka-broker kafka-topics --create \
  --bootstrap-server localhost:9092 \
  --topic mon-nouveau-topic \
  --partitions 3 \
  --replication-factor 1
```

### Supprimer un topic
```bash
docker exec kafka-broker kafka-topics --delete --topic nom-du-topic --bootstrap-server localhost:9092
```

## 🛑 Arrêt et nettoyage

### Arrêter les services
```bash
docker-compose -f docker-compose-kafka.yml down
```

### Arrêter et supprimer les volumes (⚠️ supprime les données)
```bash
docker-compose -f docker-compose-kafka.yml down -v
```

## 🔍 Monitoring

### Vérifier la santé du cluster
```bash
docker exec kafka-broker kafka-broker-api-versions --bootstrap-server localhost:9092
```

### Voir les consumer groups
```bash
docker exec kafka-broker kafka-consumer-groups --list --bootstrap-server localhost:9092
```

### Détails d'un consumer group
```bash
docker exec kafka-broker kafka-consumer-groups --describe --group nom-du-group --bootstrap-server localhost:9092
```

## 🔐 Ports exposés

- `2181` : Zookeeper
- `9092` : Kafka (externe)
- `29092` : Kafka (interne Docker)
- `8080` : Kafka UI

## 📦 Personnalisation

### Ajouter de nouveaux topics

Modifiez le service `kafka-init` dans le docker-compose et ajoutez :

```bash
kafka-topics --create --if-not-exists \
  --bootstrap-server kafka:29092 \
  --topic votre-nouveau-topic \
  --partitions 3 \
  --replication-factor 1 \
  --config retention.ms=604800000
```

### Modifier la rétention des messages

- `604800000` ms = 7 jours
- `1209600000` ms = 14 jours
- `2592000000` ms = 30 jours
- `7776000000` ms = 90 jours

## ⚠️ Notes importantes

1. **Réplication** : La réplication est à 1 car nous avons un seul broker. En production, utilisez 3 brokers minimum avec une réplication de 3.

2. **Partitions** : Le nombre de partitions détermine le parallélisme. Plus de partitions = plus de throughput mais plus de ressources.

3. **Rétention** : Ajustez selon vos besoins de stockage et de compliance.

4. **Production** : En production, ajoutez :
    - Authentication (SASL)
    - Encryption (SSL/TLS)
    - Plus de brokers pour la haute disponibilité
    - Monitoring (Prometheus + Grafana)

## 🔗 Intégration avec votre architecture

Les topics sont alignés avec vos microservices :
- `notification-topic` → service notification
- `appointment-events` → service appointment
- `payment-events` → service paiement
- `user-events` → service users
- `message-events` → service messagerie
- `call-events` → service call-video
- `search-index-events` → service search