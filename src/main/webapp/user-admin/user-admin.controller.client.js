(function () {
    let currentUser;
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
        $updateBtn.click(() => updateUser());

        findAllUsers();
    }

    function createUser() {
        if (!isValidInput()) {
            alert("Please fill in all fields");
            return;
        }
        const user = new User($usernameFld.val(), $passwordFld.val(), $firstNameFld.val(),
                              $lastNameFld.val(), $roleFld.val());
        clearFld();
        userService.createUser(user).then(newUser => findAllUsers());
    }

    function findAllUsers() {
        userService.findAllUsers().then(users => renderUsers(users));
    }

    function findUserById(id) {
        userService.findUserById(id)
            .then(user => {
                currentUser = user;
                selectUser();
            });
    }

    function deleteUser(id) {
        userService.deleteUser(id).then(response => findAllUsers());
    }

    function selectUser() {
        $usernameFld.val(currentUser.username);
        $firstNameFld.val(currentUser.firstName);
        $lastNameFld.val(currentUser.lastName);
        $roleFld.val(currentUser.role);
        $passwordFld.attr("title", "Enter new password or leave blank to keep current password");
    }

    function updateUser() {
        if (currentUser == null) {
            alert("Please select a user to update");
            return;
        }
        if (!isValidEdit()) {
            alert("Username/First Name/Last Name cannot be empty");
            return;
        }

        if ($passwordFld.val() !== "") {
            currentUser.password = $passwordFld.val();
        }
        currentUser.username = $usernameFld.val();
        currentUser.firstName = $firstNameFld.val();
        currentUser.lastName = $lastNameFld.val();
        currentUser.role = $roleFld.val();

        userService.updateUser(currentUser._id, currentUser).then(newUser => findAllUsers());

        clearFld();
        currentUser = null;
    }

    function renderUser(user) {
        let $userRow = $userRowTemplate.clone();
        $userRow.removeClass("d-none");
        $userRow.removeClass("wbdv-template");
        $userRow.removeClass("wbdv-hidden");

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
        $userId.val("");
    }

    function isValidInput() {
        return $usernameFld.val() !== "" && $passwordFld.val() !== "" && $firstNameFld.val() !== ""
               && $lastNameFld.val() !== "";
    }

    function isValidEdit() {
        return $usernameFld.val() !== "" && $firstNameFld.val() !== "" && $lastNameFld.val() !== "";
    }
})();