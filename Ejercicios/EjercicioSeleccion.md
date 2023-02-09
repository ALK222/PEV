# Ejercicio Selección

### Alejandro Barrachina Argudo

| Individuo | Fitness u<sub>i</sub> | Puntuación p<sub>i</sub> | acumulada q<sub>i</sub> |
| --------- | --------------------- | ------------------------ | ----------------------- |
| 1         | 4                     | 0.2                      | 0.2                     |
| 2         | 1                     | 0.05                     | 0.25                    |
| 3         | 1                     | 0.05                     | 0.3                     |
| 4         | 2                     | 0.1                      | 0.4                     |
| 5         | 3                     | 0.15                     | 0.55                    |
| 6         | 2                     | 0.1                      | 0.65                    |
| 7         | 5                     | 0.25                     | 0.9                     |
| 8         | 2                     | 0.1                      | 1                       |
|           | 20                    |                          |

## Ruleta

Aplicamos una selección por ruleta de 5 individuos, cogiendo 5 valores aleatorios entre 0 y 1:

1.  0.39 -> Individuo 4
2.  0.96 -> Individuo 8
3.  0.41 -> Individuo 5
4.  0.28 -> Individuo 3
5.  0.26 -> Individuo 3

## Universal Estocástico

Aplicamos una selección universal estocástica de población 5 con r = 0.1

- a<sub>1</sub> = (0.1+1-1)/5 = 0.02 -> Individuo 1
- a<sub>2</sub> = (0.1+2-1)/5 = 0.22 -> Individuo 2
- a<sub>3</sub> = (0.1+3-1)/5 = 0.42 -> Individuo 5
- a<sub>4</sub> = (0.1+4-1)/5 = 0.62 -> Individuo 7
- a<sub>5</sub> = (0.1+5-1)/5 = 0.82 -> Individuo 7

## Torneo probabilístico y determinístico con muestras de tamaño 3

Escogemos la primera selección de elementos: [2, 2, 5] -> 5
Escogemos la segunda selección de elementos: [6, 3, 1] -> 1
Escogemos la tercera selección de elementos: [5, 4, 8] -> 5
Escogemos la cuarta selección de elementos: [6, 2, 3] -> 6
Escogemos la quinta selección de elementos: [3, 3, 7] -> 7
Escogemos la sexta selección de elementos: [6, 3, 8] -> 6
Escogemos la séptima selección de elementos: [8, 2, 5] -> 5
Escogemos la octava selección de elementos: [4, 8, 6] -> 4
Escogemos la novena selección de elementos: [6, 3, 4] -> 6
Escogemos la décima selección de elementos: [6, 8, 5] -> 5

## Restos

Con k = 8 tenemos:

| Individuo | Fitness u<sub>i</sub> | Puntuación p<sub>i</sub> | acumulada q<sub>i</sub> | p<sub>i</sub>\*k |
| --------- | --------------------- | ------------------------ | ----------------------- | ---------------- |
| 1         | 4                     | 0.2                      | 0.2                     | 1.6              |
| 2         | 1                     | 0.05                     | 0.25                    | 0.4              |
| 3         | 1                     | 0.05                     | 0.3                     | 0.4              |
| 4         | 2                     | 0.1                      | 0.4                     | 0.8              |
| 5         | 3                     | 0.15                     | 0.55                    | 1.2              |
| 6         | 2                     | 0.1                      | 0.65                    | 0.8              |
| 7         | 5                     | 0.25                     | 0.9                     | 2                |
| 8         | 2                     | 0.1                      | 1                       | 0.8              |
|           | 20                    |                          |                         |                  |

Seleccionamos 1,5 y 7. El resto de individuos se escogerían mediante otro método

## Truncamiento al 50%

Obtenemos la siguiente lista:

- Individuo 7
- Individuo 7
- Individuo 1
- Individuo 1
- Individuo 5
- Individuo 5
- Individuo 4
- Individuo 4
- Individuo 6
- Individuo 6
