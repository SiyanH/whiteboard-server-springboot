(function () {
    let $usernameFld, $passwordFld;
    let $firstNameFld, $lastNameFld, $roleFld, $userId;
    let $removeBtn, $editBtn, $createBtn, $updateBtn;
    let $userRowTemplate, $tbody;
    let userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#roleFld");
        $userId = $("#userId");
        $createBtn = $("#create-btn");
        $updateBtn = $("#update-btn");
        $userRowTemplate = $(".wbdv-template");
        $tbody = $(".wbdv-tbody");

        $createBtn.click(() => createUser());
        $updateBtn.click(() => updateUser($userId.val()));

        findAllUsers();
    }

    function createUser() {
        const user = new User($usernameFld.val(), $passwordFld.val(), $firstNameFld.val(),
                              $lastNameFld.val(), $roleFld.val());
        clearFld();
        userService.createUser(user).then(newUser => findAllUsers());
    }

    function findAllUsers() {
        userService.findAllUsers().then(users => renderUsers(users));
    }

    function findUserById(id) {
        userService.findUserById(id).then(user => selectUser(user));
    }

    function deleteUser(id) {
        userService.deleteUser(id).then(response => findAllUsers());
    }

    function selectUser(user) {
        $usernameFld.val(user.username);
        $passwordFld.val(user.password);
        $firstNameFld.val(user.firstName);
        $lastNameFld.val(user.lastName);
        $roleFld.val(user.role);
        $userId.val(user._id);
    }

    function updateUser(id) {
        const user = new User($usernameFld.val(), $passwordFld.val(), $firstNameFld.val(),
                              $lastNameFld.val(), $roleFld.val());
        clearFld();
        userService.updateUser(id, user).then(newUser => findAllUsers());
    }

    function renderUser(user) {
        let $userRow = $userRowTemplate.clone();
        $userRow.removeClass("d-none");

        $userRow.children(".wbdv-username").text(user.username);
        $userRow.children(".wbdv-first-name").text(user.firstName);
        $userRow.children(".wbdv-last-name").text(user.lastName);
        $userRow.children(".wbdv-role").text(user.role);

        $removeBtn = $userRow.find("#wbdv-remove");
        $editBtn = $userRow.find("#wbdv-edit");
        $removeBtn.click(() => deleteUser(user._id));
        $editBtn.click(() => findUserById(user._id));

        $tbody.append($userRow);
    }

    function renderUsers(users) {
        $tbody.empty();
        for (let i = 0; i < users.length; i++) {
            renderUser(users[i]);
        }
    }

    function clearFld() {
        $usernameFld.val("");
        $passwordFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("FACULTY");
    }
})();