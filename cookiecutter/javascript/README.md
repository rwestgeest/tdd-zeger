# Options

### Kata name

```
kata [GameOfLife]: 
```

Name of the kata. The name of the two generatied source and 
test files will be based on this name.

### Project slug

```
project_slug [gameoflife]: 
```

This slug is automatically generated and generally doesn't
need to be modified. The directory name of the kata will be
named usiing this slug.

### Short description

```
project_short_description [This kata practices TDD]: 
```

Optional, will be used in packaging files such as the ```package.json```.


### Author

```
author [Your name]: 
```

Optional, will be used in packaging files such as the ```package.json```.

# Running the project

The following options are available to run the tests

- `npm run test` &rarr; run the test(s) only once
- `npm run watch` &rarr; run the test(s) continuously
- `npm run coverage` &rarr; run test(s) continuously _with_ code coverage reports 
   in both HTML and text
- `npm run report` &rarr; start a HTTP server that serves the HTML coverage reports 
