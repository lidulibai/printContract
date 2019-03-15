void createToken(char *name, int num, char *symbol, int precision) {
    initToken(name, num, symbol, precision);
    setBalance(num);
}

int transfer(char *to, int value) {
    if (getBalance() >= value) {
        setBalance(getBalance() - value);
        setBalance(to, getBalance(to) + value);
        return 1;
    }
    return 0;
}

int transferfrom(char* from, char* to, int value) {
    int allowance = allowed(from, getAdress());
    if (getBalance(from) >= value && allowance >= value) {
        setBalance(to, getBalance(to) + value);
        setBalance(from, getBalance(from) - value);
        if (allowance < 10000) {
            setAllowed(from, allowed(from, getAdress()) - value);
            return 1;
        }
    }
}

int balanceOf(char* owner) {
    return getBalance(owner);
}

int approve(char* spender, int value) {
    setAllowed(spender, value);
    return 1;
}

int allowance(char* owner, char* spender) {
    return allowed(owner, spender);
}
