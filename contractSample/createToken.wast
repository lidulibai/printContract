(module
 (type $FUNCSIG$i (func (result i32)))
 (type $FUNCSIG$iii (func (param i32 i32) (result i32)))
 (type $FUNCSIG$ii (func (param i32) (result i32)))
 (type $FUNCSIG$iiiii (func (param i32 i32 i32 i32) (result i32)))
 (import "env" "print" (func $print (param i32) (result i32)))
 (import "env" "printNum" (func $printNum (param i32) (result i32)))
 (import "env" "println" (func $println (param i32) (result i32)))
 (import "env" "printlnNum" (func $printlnNum (param i32) (result i32)))
 (import "env" "allowed" (func $allowed (param i32 i32) (result i32)))
 (import "env" "getAdress" (func $getAdress (result i32)))
 (import "env" "getMyBalance" (func $getMyBalance (result i32)))
 (import "env" "getOtherBalance" (func $getOtherBalance (param i32) (result i32)))
 (import "env" "initToken" (func $initToken (param i32 i32 i32 i32) (result i32)))
 (import "env" "setAllowed" (func $setAllowed (param i32 i32) (result i32)))
 (import "env" "setBalance" (func $setBalance (param i32) (result i32)))
 (import "env" "setOtherBalance" (func $setOtherBalance (param i32 i32) (result i32)))
 (table 0 anyfunc)
 (memory $0 1)
 (export "memory" (memory $0))
 (export "createToken" (func $createToken))
 (export "transfer" (func $transfer))
 (export "balanceOf" (func $balanceOf))
 (export "approve" (func $approve))
 (export "allowance" (func $allowance))
 (func $createToken (; 8 ;) (param $0 i32) (param $1 i32) (param $2 i32) (param $3 i32)
  (drop
   (call $initToken
    (get_local $0)
    (get_local $1)
    (get_local $2)
    (get_local $3)
   )
  )
  (drop
   (call $setBalance
    (get_local $1)
   )
  )
 )
 (func $transfer (; 9 ;) (param $0 i32) (param $1 i32) (result i32)
  (local $2 i32)
  (local $3 i32)
  (set_local $3
   (i32.const 0)
  )
  (block $label$0
   (br_if $label$0
    (i32.lt_s
     (tee_local $2
      (call $getMyBalance)
     )
     (get_local $1)
    )
   )
   (drop
    (call $println
     (tee_local $3
      (i32.sub
       (get_local $2)
       (get_local $1)
      )
     )
    )
   )
   (drop
    (call $setBalance
     (get_local $3)
    )
   )
   (drop
    (call $setOtherBalance
     (get_local $0)
     (i32.add
      (call $getOtherBalance
       (get_local $0)
      )
      (get_local $1)
     )
    )
   )
   (set_local $3
    (i32.const 1)
   )
  )
  (get_local $3)
 )
 (func $balanceOf (; 10 ;) (param $0 i32) (result i32)
  (call $getOtherBalance
   (get_local $0)
  )
 )
 (func $approve (; 11 ;) (param $0 i32) (param $1 i32) (result i32)
  (drop
   (call $setAllowed
    (get_local $0)
    (get_local $1)
   )
  )
  (i32.const 1)
 )
 (func $allowance (; 12 ;) (param $0 i32) (param $1 i32) (result i32)
  (call $allowed
   (get_local $0)
   (get_local $1)
  )
 )
)
