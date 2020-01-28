function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'https://wbdv-generic-server.herokuapp.com/api/hsy6337/users';
    let self = this;

    /**
     * Accept a user object and add it to a collection of users.
     */
    function createUser(user) {
        return fetch(self.url, {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                "content-type": "application/json"
            }
        }).then(response => response.json())
    }

    /**
     * Retrieve all users as an array of JSON objects.
     */
    function findAllUsers() {
        return fetch(self.url).then(response => response.json())
    }

    /**
     * Retrieve a single user JSON object whose id is equal to the id parameter.
     */
    function findUserById(id) {
        return fetch(`${self.url}/${id}`).then(response => response.json())
    }

    /**
     *  Accept a user id and user object with new property values for the user with user id.
     *  Find the user whose id matches he id parameter and update it with the values in the
     *  user parameter.
     */
    function updateUser(id, user) {
        return fetch(`${self.url}/${id}`, {
            method: 'PUT',
            body: JSON.stringify(user),
            headers: {
                "content-type": "application/json"
            }
        }).then(response => response.json())
    }

    /**
     * Removes the user whose id matches the id parameter.
     */
    function deleteUser(id) {
        return fetch(`${self.url}/${id}`, {method: "DELETE"})
    }
}